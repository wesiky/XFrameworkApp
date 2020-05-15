package com.xframework.xframeworkapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.adapter.SpareBarcodeAdapter;
import com.xframework.delegate.BaseDelegate;
import com.xframework.model.BaseBarcodeList;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.SWSpareBarcode;
import com.xframework.model.SWSpareScrap;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.BaseWSOut;
import com.xframework.model.WS.CheckSpareBarcodeOut;
import com.xframework.model.WS.SaveSpareScrapIn;
import com.xframework.util.ChintWebServiceUtil;
import com.xframework.util.EditTextHelper;
import com.xframework.util.ProgressDialogUtil;
import com.xframework.util.XFrameworkWebServiceUtil;

import static android.view.KeyEvent.ACTION_UP;
import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class SpareScrapActivity extends AppCompatActivity {
    SpareBarcodeAdapter adapter_listViewBarcode;

    BaseBarcodeList base_barcode_list = new BaseBarcodeList();

    BarcodeDelegate delegate = new BarcodeDelegate();

    EditText etBarcode;

    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();

    RecyclerView rvBarcode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            requestWindowFeature(FEATURE_NO_TITLE);
            getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
            setContentView(R.layout.activity_spare_scrap);
            etBarcode = findViewById(R.id.etBarcode);
            rvBarcode = findViewById(R.id.lvBarcode);
            rvBarcode.setLayoutManager(new LinearLayoutManager(this));

            //关闭键盘
            EditTextHelper.CloseKeyBoard(etBarcode);

            etBarcode.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == KEYCODE_ENTER && keyEvent.getAction() == ACTION_UP && !etBarcode.getText().toString().equals("")) {
                        try {
                            progressDialogUtil.showProgressDialog(SpareScrapActivity.this);
                            final String barcode = etBarcode.getText().toString().replace("\n", "");
                            int position = base_barcode_list.findBarcode(barcode);
                            if (position >= 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SpareScrapActivity.this);
                                builder.setTitle("请选择").setMessage("条码" + barcode + "已扫描，要删除吗？");
                                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        base_barcode_list.removeBarcode(barcode);
                                        adapter_listViewBarcode = new SpareBarcodeAdapter(SpareScrapActivity.this, R.layout.item_spare_barcode_chang, base_barcode_list.getBarcodes(), SpareScrapActivity.this.delegate);
                                        rvBarcode.setAdapter(adapter_listViewBarcode);
                                        Toast.makeText(SpareScrapActivity.this, "条码" + barcode + "已删除", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNeutralButton("否", null);
                                builder.show();
                                return false;
                            }
                            CheckSpareBarcodeOut ws_out = ChintWebServiceUtil.GetSpareBarcode(barcode);
                            if (ws_out.getStatus() == 0) {
                                if (ws_out.getBarcode().getSurplusQuantity() <= 0) {
                                    Toast.makeText(SpareScrapActivity.this, "条码" + barcode + "扫码失败：条码已消耗", Toast.LENGTH_LONG).show();
                                } else {
                                    SWSpareBarcode spareBarcode = ws_out.getBarcode();
                                    spareBarcode.setChangQuantity(spareBarcode.getSurplusQuantity());
                                    base_barcode_list.addBarocde(spareBarcode);
                                    adapter_listViewBarcode.notifyDataSetChanged();
                                    Toast.makeText(SpareScrapActivity.this, "条码" + barcode + "扫码成功", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(SpareScrapActivity.this, "条码" + barcode + "扫码失败：" + ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(SpareScrapActivity.this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
                        } finally {
                            progressDialogUtil.dismiss();
                            etBarcode.getText().clear();
                        }
                    }
                    return false;
                }
            });
            this.adapter_listViewBarcode = new SpareBarcodeAdapter(this, R.layout.item_spare_barcode_chang, base_barcode_list.getBarcodes(), this.delegate);
            this.rvBarcode.setAdapter(this.adapter_listViewBarcode);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void returnOnClick(View v) {
        finish();
    }

    public void saveOnClick(View v) {
        try {
            if (this.base_barcode_list.getBarcodes().size() == 0) {
                Toast.makeText(this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            SaveSpareScrapIn ws_in = new SaveSpareScrapIn();
            ws_in.setUserId(LoginUserInfo.getUserId());
            ws_in.setDeviceCode(SystemInfo.getDeviceCode());
            for (SWSpareBarcode spareBarcode : base_barcode_list.getBarcodes()) {
                SWSpareScrap recode = new SWSpareScrap();
                recode.setBarcode(spareBarcode.getBarcode());
                recode.setQuantity(spareBarcode.getChangQuantity());
                ws_in.getRecords().add(recode);
            }
            BaseWSOut ws_out = XFrameworkWebServiceUtil.API_SaveSpareScrap(ws_in);
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

    private class BarcodeDelegate implements BaseDelegate {
        private BarcodeDelegate() {}

        public boolean removeBarcodes(String barcode) {
            if (base_barcode_list != null) {
                base_barcode_list.removeBarcode(barcode);
                return true;
            }
            return false;
        }
    }
}