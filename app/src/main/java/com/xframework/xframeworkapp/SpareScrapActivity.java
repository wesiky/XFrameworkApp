package com.xframework.xframeworkapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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

public class SpareScrapActivity extends AppCompatActivity {
    SpareBarcodeAdapter adapter_listViewBarcode;

    BaseBarcodeList base_barcode_list = new BaseBarcodeList();

    BarcodeDelegate delegate = new BarcodeDelegate();

    EditText etBarcode;

    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();

    RecyclerView rvBarcode;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        try {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            setContentView(R.layout.activity_spare_scrap);
            this.etBarcode = (EditText)findViewById(R.id.etBarcode);
            this.rvBarcode = (RecyclerView)findViewById(R.id.lvBarcode);
            this.rvBarcode.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager((Context)this));
            EditTextHelper.CloseKeyBoard(this.etBarcode);
            this.etBarcode.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                    if (param1KeyEvent.getKeyCode() == 66 && param1KeyEvent.getAction() == 1 && !SpareScrapActivity.this.etBarcode.getText().toString().equals("")) {
                        try {
                            SpareScrapActivity spareScrapActivity;
                            SpareScrapActivity.this.progressDialogUtil.showProgressDialog((Context)SpareScrapActivity.this);
                            final String barcode = SpareScrapActivity.this.etBarcode.getText().toString().replace("\n", "");
                            param1Int = SpareScrapActivity.this.base_barcode_list.findBarcode(barcode);
                            if (param1Int >= 0) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder((Context)SpareScrapActivity.this);
                                AlertDialog.Builder builder2 = builder1.setTitle("请选择");
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(barcode);
                                stringBuilder.append("已扫描，要删除吗？");
                                builder2.setMessage(stringBuilder.toString());
                                builder1.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                        SpareScrapActivity.this.base_barcode_list.removeBarcode(barcode);
                                        SpareScrapActivity.this.adapter_listViewBarcode = new SpareBarcodeAdapter((Context)SpareScrapActivity.this, 2131427416, SpareScrapActivity.this.base_barcode_list.getBarcodes(), SpareScrapActivity.this.delegate);
                                        SpareScrapActivity.this.rvBarcode.setAdapter((RecyclerView.Adapter)SpareScrapActivity.this.adapter_listViewBarcode);
                                        SpareScrapActivity spareScrapActivity = SpareScrapActivity.this;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("条码");
                                        stringBuilder.append(barcode);
                                        stringBuilder.append("已删除");
                                        Toast.makeText((Context)spareScrapActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                    }
                                });
                                builder1.setNeutralButton("否", null);
                                builder1.show();
                                SpareScrapActivity.this.progressDialogUtil.dismiss();
                                SpareScrapActivity.this.etBarcode.getText().clear();
                                return false;
                            }
                            CheckSpareBarcodeOut checkSpareBarcodeOut = ChintWebServiceUtil.GetSpareBarcode(barcode);
                            if (checkSpareBarcodeOut.getStatus() == 0) {
                                if (checkSpareBarcodeOut.getBarcode().getSurplusQuantity() <= 0.0F) {
                                    spareScrapActivity = SpareScrapActivity.this;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("条码");
                                    stringBuilder.append(SpareScrapActivity.this.etBarcode.getText());
                                    stringBuilder.append("扫码失败：条码已消耗");
                                    Toast.makeText((Context)spareScrapActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                } else {
                                    SWSpareBarcode sWSpareBarcode = checkSpareBarcodeOut.getBarcode();
                                    sWSpareBarcode.setChangQuantity(sWSpareBarcode.getSurplusQuantity());
                                    SpareScrapActivity.this.base_barcode_list.addBarocde(sWSpareBarcode);
                                    SpareScrapActivity.this.adapter_listViewBarcode.notifyDataSetChanged();
                                    spareScrapActivity = SpareScrapActivity.this;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("条码");
                                    stringBuilder.append(SpareScrapActivity.this.etBarcode.getText());
                                    stringBuilder.append("扫码成功");
                                    Toast.makeText((Context)spareScrapActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                SpareScrapActivity spareScrapActivity1 = SpareScrapActivity.this;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(SpareScrapActivity.this.etBarcode.getText());
                                stringBuilder.append("扫码失败：");
                                stringBuilder.append(checkSpareBarcodeOut.getStatus());
                                stringBuilder.append(":");
                                stringBuilder.append(checkSpareBarcodeOut.getMsg());
                                Toast.makeText((Context)spareScrapActivity1, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception exception) {
                            StringBuilder stringBuilder1 = new StringBuilder();
                            stringBuilder1.append(SpareScrapActivity.this.getClass().getName());
                            stringBuilder1.append(" onCreate: ");
                            stringBuilder1.append(exception.getMessage());
                            Log.e("SystemError", stringBuilder1.toString());
                            SpareScrapActivity spareScrapActivity = SpareScrapActivity.this;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("程序异常，请联系管理员，异常原因：");
                            stringBuilder2.append(exception.getMessage());
                            Toast.makeText((Context)spareScrapActivity, stringBuilder2.toString(), 1).show();
                        } finally {}
                        SpareScrapActivity.this.progressDialogUtil.dismiss();
                        SpareScrapActivity.this.etBarcode.getText().clear();
                        return false;
                    }
                    return false;
                }
            });
            this.adapter_listViewBarcode = new SpareBarcodeAdapter((Context)this, 2131427416, this.base_barcode_list.getBarcodes(), this.delegate);
            this.rvBarcode.setAdapter((RecyclerView.Adapter)this.adapter_listViewBarcode);
            return;
        } catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getClass().getName());
            stringBuilder.append(" onCreate: ");
            stringBuilder.append(exception.getMessage());
            Log.e("SystemError", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("程序异常，请联系管理员，异常原因：");
            stringBuilder.append(exception.getMessage());
            Toast.makeText((Context)this, stringBuilder.toString(), 1).show();
            finish();
            return;
        }
    }

    public void returnOnClick(View paramView) {
        finish();
    }

    public void saveOnClick(View paramView) {
        try {
            if (this.base_barcode_list.getBarcodes().size() == 0) {
                Toast.makeText((Context)this, "条码为空，请扫码后再保存！", 1).show();
                return;
            }
            SaveSpareScrapIn saveSpareScrapIn = new SaveSpareScrapIn();
            saveSpareScrapIn.setUserId(LoginUserInfo.getUserId());
            saveSpareScrapIn.setDeviceCode(SystemInfo.getDeviceCode());
            for (SWSpareBarcode sWSpareBarcode : this.base_barcode_list.getBarcodes()) {
                SWSpareScrap sWSpareScrap = new SWSpareScrap();
                sWSpareScrap.setBarcode(sWSpareBarcode.getBarcode());
                sWSpareScrap.setQuantity(sWSpareBarcode.getChangQuantity());
                saveSpareScrapIn.getRecords().add(sWSpareScrap);
            }
            BaseWSOut baseWSOut = XFrameworkWebServiceUtil.API_SaveSpareScrap(saveSpareScrapIn);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(baseWSOut.getStatus());
            stringBuilder.append(":");
            stringBuilder.append(baseWSOut.getMsg());
            Toast.makeText((Context)this, stringBuilder.toString(), 1).show();
            if (baseWSOut.getStatus() == 0)
                finish();
            return;
        } catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getClass().getName());
            stringBuilder.append(" saveOnClick: ");
            stringBuilder.append(exception.getMessage());
            Log.e("SystemError", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("程序异常，请联系管理员，异常原因：");
            stringBuilder.append(exception.getMessage());
            Toast.makeText((Context)this, stringBuilder.toString(), 1).show();
            finish();
            return;
        }
    }

    private class BarcodeDelegate implements BaseDelegate {
        private BarcodeDelegate() {}

        public boolean removeBarcodes(String param1String) {
            if (SpareScrapActivity.this.base_barcode_list != null) {
                SpareScrapActivity.this.base_barcode_list.removeBarcode(param1String);
                return true;
            }
            return false;
        }
    }
}


/* Location:              C:\test\dex2jar-2.0\classes2-dex2jar.jar!\com\xframework\xframeworkapp\SpareScrapActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */