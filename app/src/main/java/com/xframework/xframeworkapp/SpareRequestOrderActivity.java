package com.xframework.xframeworkapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.xframework.adapter.ListViewSpareAdapter;
import com.xframework.model.BaseData;
import com.xframework.model.BaseOrderBarcode;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.SWRequestOrderHead;
import com.xframework.model.SWSpareBarcode;
import com.xframework.model.SpareBarcodeList;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.BaseWSOut;
import com.xframework.model.WS.CheckSpareBarcodeOut;
import com.xframework.model.WS.GetOrderCodeIn;
import com.xframework.model.WS.GetOrderCodeOut;
import com.xframework.model.WS.SaveSpareRequestIn;
import com.xframework.util.ChintWebServiceUtil;
import com.xframework.util.EditTextHelper;
import com.xframework.util.ProgressDialogUtil;
import com.xframework.util.XFrameworkWebServiceUtil;

import static android.view.KeyEvent.ACTION_UP;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class SpareRequestOrderActivity extends AppCompatActivity {
    ListViewSpareAdapter adapter_listViewSpare;

    EditText etBarcode;

    TextView etOrderCode;

    EditText etRequestUser;

    ListView lvSpare;

    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();

    SpareBarcodeList spare_barcode_list = new SpareBarcodeList();

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            try {
                spare_barcode_list = (SpareBarcodeList)data.getSerializableExtra("spare_barcode_list");
                adapter_listViewSpare = new ListViewSpareAdapter(this, R.layout.item_spare, this.spare_barcode_list.getItems(), 0);
                lvSpare.setAdapter(adapter_listViewSpare);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            requestWindowFeature(FEATURE_NO_TITLE);
            getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
            setContentView(R.layout.activity_spare_request_order);
            etOrderCode = findViewById(R.id.etOrderCode);
            etRequestUser = findViewById(R.id.etRequestUser);
            etBarcode = findViewById(R.id.etBarcode);
            GetOrderCodeIn ws_in = new GetOrderCodeIn();
            ws_in.setUserId(LoginUserInfo.getUserId());
            ws_in.setType(6);
            ws_in.setDeviceCode(SystemInfo.getDeviceCode());
            GetOrderCodeOut ws_out = XFrameworkWebServiceUtil.API_GetSpareOrderCode(ws_in);
            if (ws_out.getStatus() == 0) {
                etOrderCode.setText(ws_out.getOrderCode());
            } else {
                Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
                finish();
            }
            this.lvSpare = findViewById(R.id.lvSpare);
            TextView emptyView = new TextView(this);
            emptyView.setLayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            emptyView.setText("未扫描数据");
            emptyView.setGravity(Gravity.CENTER);
            emptyView.setVisibility(View.GONE);
            ((ViewGroup)lvSpare.getParent()).addView(emptyView);
            lvSpare.setEmptyView(emptyView);

            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

            //关闭键盘
            EditTextHelper.CloseKeyBoard(etRequestUser);
            EditTextHelper.CloseKeyBoard(etBarcode);

            //维修工选择事件
            this.etRequestUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SpareRequestOrderActivity.this);
                        builder.setTitle("请选择");
                        final String[] repairmans = new String[BaseData.getRepairmans().size()];
                        BaseData.getRepairmans().toArray((Object[])repairmans);
                        builder.setItems(repairmans, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                etRequestUser.setText(repairmans[i]);
                                etBarcode.requestFocus();
                            }
                        });
                        builder.show();
                    }
                }
            });

            //条码扫描事件
            this.etBarcode.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == KEYCODE_ENTER && keyEvent.getAction() == ACTION_UP && !etBarcode.getText().toString().equals("")) {
                        try {
                            progressDialogUtil.showProgressDialog(SpareRequestOrderActivity.this);
                            final String barcode = etBarcode.getText().toString().replace("\n", "");
                            int position = SpareRequestOrderActivity.this.spare_barcode_list.findBarcode(barcode);
                            if (position >= 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SpareRequestOrderActivity.this);
                                builder.setTitle("请选择").setMessage("条码" + barcode + "已扫描，要删除吗？");
                                builder.setPositiveButton("是",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        spare_barcode_list.removeBarcode(barcode);
                                        adapter_listViewSpare = new ListViewSpareAdapter(SpareRequestOrderActivity.this, R.layout.item_spare, spare_barcode_list.getItems(),0);
                                        lvSpare.setAdapter(adapter_listViewSpare);
                                        Toast.makeText(SpareRequestOrderActivity.this, "条码" + barcode + "已删除",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNeutralButton("否",null);
                                builder.show();
                                return false;
                            }
                            CheckSpareBarcodeOut ws_out = ChintWebServiceUtil.GetSpareBarcode(barcode);
                            if (ws_out.getStatus() == 0) {
                                SWSpareBarcode sWSpareBarcode = ws_out.getBarcode();
                                if (sWSpareBarcode.getSurplusQuantity() <= 0) {
                                    Toast.makeText(SpareRequestOrderActivity.this, "条码" + barcode + "扫码失败：条码已消耗", Toast.LENGTH_LONG).show();
                                } else {
                                    sWSpareBarcode.setChangQuantity(sWSpareBarcode.getSurplusQuantity());
                                    spare_barcode_list.addBarocde(sWSpareBarcode);
                                    adapter_listViewSpare.notifyDataSetChanged();
                                    Toast.makeText(SpareRequestOrderActivity.this, "条码" + barcode + "扫码成功", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(SpareRequestOrderActivity.this, "条码" + barcode + "扫码失败：" + ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(SpareRequestOrderActivity.this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
                        } finally {
                            progressDialogUtil.dismiss();
                            etBarcode.getText().clear();
                        }
                    }
                    return false;
                }
            });
            this.lvSpare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        Intent intent = new Intent(SpareRequestOrderActivity.this, SpareBarcodeListActivity.class);
                        intent.putExtra("item_index", i);
                        intent.putExtra("spare_barcode_list", SpareRequestOrderActivity.this.spare_barcode_list);
                        startActivityForResult(intent, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SpareRequestOrderActivity.this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
            adapter_listViewSpare = new ListViewSpareAdapter(this, R.layout.item_spare, this.spare_barcode_list.getItems(), 0);
            lvSpare.setAdapter(adapter_listViewSpare);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(SpareRequestOrderActivity.this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void returnOnClick(View view) {
        finish();
    }

    public void saveOnClick(View view) {
        try {
            if (etRequestUser.getText().toString().equals("")) {
                Toast.makeText(this, "请填写领料人后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            if (spare_barcode_list.getBarcodes().size() == 0) {
                Toast.makeText(this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            SaveSpareRequestIn ws_in = new SaveSpareRequestIn();
            ws_in.setUserId(LoginUserInfo.getUserId());
            ws_in.setDeviceCode(SystemInfo.getDeviceCode());
            SWRequestOrderHead head = new SWRequestOrderHead();
            head.setOrderCode(etOrderCode.getText().toString());
            head.setRequestUser(etRequestUser.getText().toString());
            head.setStatus(0);
            ws_in.setHead(head);
            for (SWSpareBarcode spareBarcode : this.spare_barcode_list.getBarcodes()) {
                BaseOrderBarcode orderBarcode = new BaseOrderBarcode();
                orderBarcode.setAllocationId(spareBarcode.getAllocationId());
                orderBarcode.setBarcode(spareBarcode.getBarcode());
                orderBarcode.setQuantity(spareBarcode.getChangQuantity());
                orderBarcode.setSpareId(spareBarcode.getSpareId());
                orderBarcode.setPcs(spareBarcode.getPcs());
                orderBarcode.setSurplusQuantity(spareBarcode.getSurplusQuantity());
                orderBarcode.setUnit(spareBarcode.getUnit());
                orderBarcode.setPrice(spareBarcode.getPrice());
                orderBarcode.setBrand(spareBarcode.getBrand());
                ws_in.getBarcodes().add(orderBarcode);
            }
            BaseWSOut ws_out = XFrameworkWebServiceUtil.API_SaveSpareRequestOrder(ws_in);
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            if (ws_out.getStatus() == 0) {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }
}