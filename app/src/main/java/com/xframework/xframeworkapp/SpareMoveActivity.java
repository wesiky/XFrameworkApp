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
import com.xframework.adapter.SpareReceivedBarcodeAdapter;
import com.xframework.delegate.BaseDelegate;
import com.xframework.model.BaseAllocation;
import com.xframework.model.BaseBarcodeList;
import com.xframework.model.BaseData;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.SWSpareBarcode;
import com.xframework.model.SWSpareMove;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.BaseWSOut;
import com.xframework.model.WS.CheckSpareBarcodeOut;
import com.xframework.model.WS.SaveSpareMoveIn;
import com.xframework.util.ChintWebServiceUtil;
import com.xframework.util.EditTextHelper;
import com.xframework.util.ProgressDialogUtil;
import com.xframework.util.XFrameworkWebServiceUtil;

public class SpareMoveActivity extends AppCompatActivity {
    SpareReceivedBarcodeAdapter adapter_listViewBarcode;

    BaseAllocation allocation;

    BaseBarcodeList base_barcode_list = new BaseBarcodeList();

    BarcodeDelegate delegate = new BarcodeDelegate();

    EditText etAllocation;

    EditText etBarcode;

    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();

    RecyclerView rvBarcode;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        try {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            setContentView(R.layout.activity_spare_move);
            this.etBarcode = (EditText)findViewById(R.id.etBarcode);
            this.etAllocation = (EditText)findViewById(R.id.etAllocation);
            this.rvBarcode = (RecyclerView)findViewById(R.id.lvBarcode);
            this.rvBarcode.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager((Context)this));
            EditTextHelper.CloseKeyBoard(this.etAllocation);
            EditTextHelper.CloseKeyBoard(this.etBarcode);
            this.etAllocation.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                    if (param1KeyEvent.getKeyCode() == 66 && param1KeyEvent.getAction() == 1 && !SpareMoveActivity.this.etAllocation.getText().toString().equals(""))
                        try {
                            String str = SpareMoveActivity.this.etAllocation.getText().toString().replace("\n", "");
                            param1Int = BaseData.containsAllocation(str);
                            if (param1Int >= 0) {
                                SpareMoveActivity.this.allocation = BaseData.getAllocations().get(param1Int);
                                SpareMoveActivity.this.etAllocation.setText(str);
                                SpareMoveActivity.this.etBarcode.requestFocus();
                            } else {
                                Toast.makeText((Context)SpareMoveActivity.this, "货位不存在，请扫描正确货位条码", Toast.LENGTH_LONG).show();
                                if (SpareMoveActivity.this.allocation == null) {
                                    SpareMoveActivity.this.etAllocation.setText("");
                                } else {
                                    SpareMoveActivity.this.etAllocation.setText(SpareMoveActivity.this.allocation.getAllocationCode());
                                }
                            }
                            return true;
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    return true;
                }
            });
            this.etBarcode.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                    if (param1KeyEvent.getKeyCode() == 66 && param1KeyEvent.getAction() == 1 && !SpareMoveActivity.this.etBarcode.getText().toString().equals("")) {
                        try {
                            SpareMoveActivity spareMoveActivity;
                            SpareMoveActivity.this.progressDialogUtil.showProgressDialog((Context)SpareMoveActivity.this);
                            if (SpareMoveActivity.this.allocation == null) {
                                Toast.makeText((Context)SpareMoveActivity.this, "请先扫描货位", Toast.LENGTH_LONG).show();
                                SpareMoveActivity.this.progressDialogUtil.dismiss();
                                SpareMoveActivity.this.etBarcode.getText().clear();
                                return false;
                            }
                            final String barcode = SpareMoveActivity.this.etBarcode.getText().toString().replace("\n", "");
                            param1Int = SpareMoveActivity.this.base_barcode_list.findBarcode(barcode);
                            if (param1Int >= 0) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder((Context)SpareMoveActivity.this);
                                AlertDialog.Builder builder2 = builder1.setTitle("请选择");
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(barcode);
                                stringBuilder.append("已扫描，要删除吗？");
                                builder2.setMessage(stringBuilder.toString());
                                builder1.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                        SpareMoveActivity.this.base_barcode_list.removeBarcode(barcode);
                                        SpareMoveActivity.this.adapter_listViewBarcode = new SpareReceivedBarcodeAdapter((Context)SpareMoveActivity.this, R.layout.item_spare_barcode, SpareMoveActivity.this.base_barcode_list.getBarcodes(), SpareMoveActivity.this.delegate);
                                        SpareMoveActivity.this.rvBarcode.setAdapter((RecyclerView.Adapter)SpareMoveActivity.this.adapter_listViewBarcode);
                                        SpareMoveActivity spareMoveActivity = SpareMoveActivity.this;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("条码");
                                        stringBuilder.append(barcode);
                                        stringBuilder.append("已删除");
                                        Toast.makeText((Context)spareMoveActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                    }
                                });
                                builder1.setNeutralButton("否", null);
                                builder1.show();
                                SpareMoveActivity.this.progressDialogUtil.dismiss();
                                SpareMoveActivity.this.etBarcode.getText().clear();
                                return false;
                            }
                            CheckSpareBarcodeOut checkSpareBarcodeOut = ChintWebServiceUtil.GetSpareBarcode(barcode);
                            if (checkSpareBarcodeOut.getStatus() == 0) {
                                if (checkSpareBarcodeOut.getBarcode().getSurplusQuantity() <= 0.0F) {
                                    spareMoveActivity = SpareMoveActivity.this;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("条码");
                                    stringBuilder.append(SpareMoveActivity.this.etBarcode.getText());
                                    stringBuilder.append("扫码失败：条码已消耗");
                                    Toast.makeText((Context)spareMoveActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                } else {
                                    SWSpareBarcode sWSpareBarcode = checkSpareBarcodeOut.getBarcode();
                                    sWSpareBarcode.setAllocationId(SpareMoveActivity.this.allocation.getAllocationId());
                                    sWSpareBarcode.setChangQuantity(sWSpareBarcode.getSurplusQuantity());
                                    SpareMoveActivity.this.base_barcode_list.addBarocde(sWSpareBarcode);
                                    SpareMoveActivity.this.adapter_listViewBarcode.notifyDataSetChanged();
                                    spareMoveActivity = SpareMoveActivity.this;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("条码");
                                    stringBuilder.append(SpareMoveActivity.this.etBarcode.getText());
                                    stringBuilder.append("扫码成功");
                                    Toast.makeText((Context)spareMoveActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                SpareMoveActivity spareMoveActivity1 = SpareMoveActivity.this;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(SpareMoveActivity.this.etBarcode.getText());
                                stringBuilder.append("扫码失败：");
                                stringBuilder.append(checkSpareBarcodeOut.getStatus());
                                stringBuilder.append(":");
                                stringBuilder.append(checkSpareBarcodeOut.getMsg());
                                Toast.makeText((Context)spareMoveActivity1, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception exception) {
                            StringBuilder stringBuilder1 = new StringBuilder();
                            stringBuilder1.append(SpareMoveActivity.this.getClass().getName());
                            stringBuilder1.append(" onCreate: ");
                            stringBuilder1.append(exception.getMessage());
                            Log.e("SystemError", stringBuilder1.toString());
                            SpareMoveActivity spareMoveActivity = SpareMoveActivity.this;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("程序异常，请联系管理员，异常原因：");
                            stringBuilder2.append(exception.getMessage());
                            Toast.makeText((Context)spareMoveActivity, stringBuilder2.toString(), Toast.LENGTH_LONG).show();
                        } finally {}
                        SpareMoveActivity.this.progressDialogUtil.dismiss();
                        SpareMoveActivity.this.etBarcode.getText().clear();
                        return false;
                    }
                    return false;
                }
            });
            this.adapter_listViewBarcode = new SpareReceivedBarcodeAdapter((Context)this, R.layout.item_spare_barcode, this.base_barcode_list.getBarcodes(), this.delegate);
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
            Toast.makeText((Context)this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
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
                Toast.makeText((Context)this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            SaveSpareMoveIn saveSpareMoveIn = new SaveSpareMoveIn();
            saveSpareMoveIn.setUserId(LoginUserInfo.getUserId());
            saveSpareMoveIn.setDeviceCode(SystemInfo.getDeviceCode());
            for (SWSpareBarcode sWSpareBarcode : this.base_barcode_list.getBarcodes()) {
                SWSpareMove sWSpareMove = new SWSpareMove();
                sWSpareMove.setBarcode(sWSpareBarcode.getBarcode());
                sWSpareMove.setInAllocationId(sWSpareBarcode.getAllocationId());
                saveSpareMoveIn.getRecords().add(sWSpareMove);
            }
            BaseWSOut baseWSOut = XFrameworkWebServiceUtil.API_SaveSpareMove(saveSpareMoveIn);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(baseWSOut.getStatus());
            stringBuilder.append(":");
            stringBuilder.append(baseWSOut.getMsg());
            Toast.makeText((Context)this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
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
            Toast.makeText((Context)this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    private class BarcodeDelegate implements BaseDelegate {
        private BarcodeDelegate() {}

        public boolean removeBarcodes(String param1String) {
            if (SpareMoveActivity.this.base_barcode_list != null) {
                SpareMoveActivity.this.base_barcode_list.removeBarcode(param1String);
                return true;
            }
            return false;
        }
    }
}


/* Location:              C:\test\dex2jar-2.0\classes2-dex2jar.jar!\com\xframework\xframeworkapp\SpareMoveActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */