package com.xframework.xframeworkapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.xframework.adapter.ComponentBatchAdapter;
import com.xframework.adapter.LeftAdapter;
import com.xframework.adapter.ListViewSpareAdapter;
import com.xframework.adapter.RightAdapter;
import com.xframework.delegate.BaseDelegate;
import com.xframework.model.BaseBeltline;
import com.xframework.model.BaseWorks;
import com.xframework.model.ComponentBatchBarcodeList;
import com.xframework.model.LTDelivery;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.SWSpareBarcode;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.BaseWSOut;
import com.xframework.model.WS.CheckSpareBarcodeOut;
import com.xframework.model.WS.SaveLotTrackingDeliveryIn;
import com.xframework.util.ChintWebServiceUtil;
import com.xframework.util.EditTextHelper;
import com.xframework.util.ProgressDialogUtil;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.util.ArrayList;

import static android.view.KeyEvent.ACTION_UP;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class ComponentDeliveryActivity extends AppCompatActivity implements BaseDelegate {

    int beltlineId = -1;
    TextView tvBeltlineName;
    EditText etBarcode;
    RecyclerView rvBatchList;
    ComponentBatchAdapter adapter;

    private ArrayList<BaseWorks> works_list = new ArrayList<>();
    private ArrayList<BaseBeltline> beltline_list = new ArrayList<>();

    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();

    ComponentBatchBarcodeList barcode_list = new ComponentBatchBarcodeList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(FEATURE_NO_TITLE);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        setContentView(R.layout.activity_component_delivery);
        initView();
    }

    private void initView() {
        tvBeltlineName = findViewById(R.id.tvBeltlineName);
        etBarcode = findViewById(R.id.etBarcode);
        rvBatchList = findViewById(R.id.rvBatchList);

        Intent intent = getIntent();
        beltlineId = intent.getIntExtra("beltline_id",-1);
        tvBeltlineName.setText(intent.getStringExtra("beltline_name"));

        //关闭键盘
        etBarcode.setInputType(InputType.TYPE_NULL);
        EditTextHelper.CloseKeyBoard(etBarcode);
        etBarcode.clearFocus();

        rvBatchList.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) rvBatchList.getItemAnimator()).setSupportsChangeAnimations(false);
        adapter = new ComponentBatchAdapter(this, R.layout.item_component_batch, barcode_list.getBarcodes(), this);
        rvBatchList.setAdapter(adapter);
        //条码扫描事件
        this.etBarcode.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KEYCODE_ENTER && keyEvent.getAction() == ACTION_UP && !etBarcode.getText().toString().equals("")) {
                    try {
                        progressDialogUtil.showProgressDialog(ComponentDeliveryActivity.this);
                        final String barcode = etBarcode.getText().toString().replace("\n", "");
                        if (barcode_list.findBarcode(barcode) < 0) {
                            LTDelivery ltBarcode = new LTDelivery();
                            ltBarcode.setBarcode(barcode);
                            ltBarcode.setBeltlineId(beltlineId);
                            ltBarcode.setQty(0);
                            ltBarcode.setDeliveryType(0);

                            if(ltBarcode.getBarcode().equals("")){
                                Toast.makeText(ComponentDeliveryActivity.this, "条码" + barcode + "不正确", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                barcode_list.addBarocde(ltBarcode);

                                adapter.setItems(barcode_list.getBarcodes());
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ComponentDeliveryActivity.this);
                            builder.setTitle("请选择").setMessage("条码" + barcode + "已扫描，要删除吗？");
                            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    barcode_list.removeBarcode(barcode);
                                    adapter.setItems(barcode_list.getBarcodes());
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(ComponentDeliveryActivity.this, "条码" + barcode + "已删除", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.setNeutralButton("否", null);
                            builder.show();
                            return false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ComponentDeliveryActivity.this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
                    } finally {
                        progressDialogUtil.dismiss();
                        etBarcode.getText().clear();
                    }
                }
                return false;
            }
        });
    }

    public void returnOnClick(View v) {
        this.finish();
    }

    public void saveOnClick(View v) {
        if (barcode_list.getBarcodes().size() == 0) {
            Toast.makeText(this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
            return;
        }
        SaveLotTrackingDeliveryIn ws_in = new SaveLotTrackingDeliveryIn();

        //设置基础数据
        ws_in.setUserId(LoginUserInfo.getUserId());
        ws_in.setDeviceCode(SystemInfo.getDeviceCode());
        ws_in.setBarcodes(barcode_list.getBarcodes());

        //保存单据
        BaseWSOut ws_out = XFrameworkWebServiceUtil.API_SaveLotTrackingDelivery(ws_in);

        Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
        //关闭本页
        if (ws_out.getStatus() == 0) {
            this.finish();
        }
    }

    @Override
    public boolean removeBarcodes(String barcode) {
        return false;
    }
}