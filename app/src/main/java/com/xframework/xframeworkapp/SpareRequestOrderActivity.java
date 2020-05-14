package com.xframework.xframeworkapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
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
import java.io.Serializable;

public class SpareRequestOrderActivity extends AppCompatActivity {
    ListViewSpareAdapter adapter_listViewSpare;

    EditText etBarcode;

    TextView etOrderCode;

    EditText etRequestUser;

    ListView lvSpare;

    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();

    SpareBarcodeList spare_barcode_list = new SpareBarcodeList();

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt2 == -1)
            try {
                this.spare_barcode_list = (SpareBarcodeList)paramIntent.getSerializableExtra("spare_barcode_list");
                this.adapter_listViewSpare = new ListViewSpareAdapter((Context)this, 2131427414, this.spare_barcode_list.getItems(), 0);
                this.lvSpare.setAdapter((ListAdapter)this.adapter_listViewSpare);
            } catch (Exception exception) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getClass().getName());
                stringBuilder.append(" onActivityResult: ");
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

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        try {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            setContentView(R.layout.activity_spare_request_order);
            this.etOrderCode = (TextView)findViewById(R.id.etOrderCode);
            this.etRequestUser = (EditText)findViewById(R.id.etRequestUser);
            this.etBarcode = (EditText)findViewById(R.id.etBarcode);
            GetOrderCodeIn getOrderCodeIn = new GetOrderCodeIn();
            getOrderCodeIn.setUserId(LoginUserInfo.getUserId());
            getOrderCodeIn.setType(6);
            getOrderCodeIn.setDeviceCode(SystemInfo.getDeviceCode());
            GetOrderCodeOut getOrderCodeOut = XFrameworkWebServiceUtil.API_GetSpareOrderCode(getOrderCodeIn);
            if (getOrderCodeOut.getStatus() == 0) {
                this.etOrderCode.setText(getOrderCodeOut.getOrderCode());
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getOrderCodeOut.getStatus());
                stringBuilder.append(":");
                stringBuilder.append(getOrderCodeOut.getMsg());
                Toast.makeText((Context)this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                finish();
            }
            this.lvSpare = (ListView)findViewById(R.id.lvSpare);
            TextView textView = new TextView((Context)this);
            textView.setLayoutParams((ViewGroup.LayoutParams)new WindowManager.LayoutParams(-1, -1));
            textView.setText("无数据");
            textView.setGravity(17);
            textView.setVisibility(View.GONE);
            ((ViewGroup)this.lvSpare.getParent()).addView((View)textView);
            this.lvSpare.setEmptyView((View)textView);
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            EditTextHelper.CloseKeyBoard(this.etRequestUser);
            EditTextHelper.CloseKeyBoard(this.etBarcode);
            this.etRequestUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View param1View, boolean param1Boolean) {
                    if (param1Boolean) {
                        AlertDialog.Builder builder = new AlertDialog.Builder((Context)SpareRequestOrderActivity.this);
                        builder.setTitle("请选择");
                        final String[] repairmans = new String[BaseData.getRepairmans().size()];
                        BaseData.getRepairmans().toArray((Object[])repairmans);
                        builder.setItems((CharSequence[])repairmans, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                SpareRequestOrderActivity.this.etRequestUser.setText(repairmans[param2Int]);
                                SpareRequestOrderActivity.this.etBarcode.requestFocus();
                            }
                        });
                        builder.show();
                    }
                }
            });
            this.etBarcode.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                    if (param1KeyEvent.getKeyCode() == 66 && param1KeyEvent.getAction() == 1 && !SpareRequestOrderActivity.this.etBarcode.getText().toString().equals("")) {
                        try {
                            SpareRequestOrderActivity spareRequestOrderActivity;
                            SpareRequestOrderActivity.this.progressDialogUtil.showProgressDialog((Context)SpareRequestOrderActivity.this);
                            final String barcode = SpareRequestOrderActivity.this.etBarcode.getText().toString().replace("\n", "");
                            param1Int = SpareRequestOrderActivity.this.spare_barcode_list.findBarcode(barcode);
                            if (param1Int >= 0) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder((Context)SpareRequestOrderActivity.this);
                                AlertDialog.Builder builder2 = builder1.setTitle("请选择");
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(barcode);
                                stringBuilder.append("已扫描，要删除吗？");
                                builder2.setMessage(stringBuilder.toString());
                                builder1.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                        SpareRequestOrderActivity.this.spare_barcode_list.removeBarcode(barcode);
                                        SpareRequestOrderActivity.this.adapter_listViewSpare = new ListViewSpareAdapter((Context)SpareRequestOrderActivity.this, 2131427414, SpareRequestOrderActivity.this.spare_barcode_list.getItems(), 0);
                                        SpareRequestOrderActivity.this.lvSpare.setAdapter((ListAdapter)SpareRequestOrderActivity.this.adapter_listViewSpare);
                                        SpareRequestOrderActivity spareRequestOrderActivity = SpareRequestOrderActivity.this;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("条码");
                                        stringBuilder.append(barcode);
                                        stringBuilder.append("已删除");
                                        Toast.makeText((Context)spareRequestOrderActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                    }
                                });
                                builder1.setNeutralButton("否", null);
                                builder1.show();
                                SpareRequestOrderActivity.this.progressDialogUtil.dismiss();
                                SpareRequestOrderActivity.this.etBarcode.getText().clear();
                                return false;
                            }
                            CheckSpareBarcodeOut checkSpareBarcodeOut = ChintWebServiceUtil.GetSpareBarcode(barcode);
                            if (checkSpareBarcodeOut.getStatus() == 0) {
                                SWSpareBarcode sWSpareBarcode = checkSpareBarcodeOut.getBarcode();
                                if (sWSpareBarcode.getSurplusQuantity() <= 0.0F) {
                                    spareRequestOrderActivity = SpareRequestOrderActivity.this;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("条码");
                                    stringBuilder.append(SpareRequestOrderActivity.this.etBarcode.getText());
                                    stringBuilder.append("扫码失败：条码已消耗");
                                    Toast.makeText((Context)spareRequestOrderActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                } else {
                                    sWSpareBarcode.setChangQuantity(sWSpareBarcode.getSurplusQuantity());
                                    spare_barcode_list.addBarocde((SWSpareBarcode)sWSpareBarcode);
                                    adapter_listViewSpare.notifyDataSetChanged();
                                    spareRequestOrderActivity = SpareRequestOrderActivity.this;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("条码");
                                    stringBuilder.append(SpareRequestOrderActivity.this.etBarcode.getText());
                                    stringBuilder.append("扫码成功");
                                    Toast.makeText((Context)spareRequestOrderActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                SpareRequestOrderActivity spareRequestOrderActivity1 = SpareRequestOrderActivity.this;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(SpareRequestOrderActivity.this.etBarcode.getText());
                                stringBuilder.append("扫码失败：");
                                stringBuilder.append(checkSpareBarcodeOut.getStatus());
                                stringBuilder.append(":");
                                stringBuilder.append(checkSpareBarcodeOut.getMsg());
                                Toast.makeText((Context)spareRequestOrderActivity1, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception exception) {
                            StringBuilder stringBuilder1 = new StringBuilder();
                            stringBuilder1.append(SpareRequestOrderActivity.this.getClass().getName());
                            stringBuilder1.append(" onCreate: ");
                            stringBuilder1.append(exception.getMessage());
                            Log.e("SystemError", stringBuilder1.toString());
                            SpareRequestOrderActivity spareRequestOrderActivity = SpareRequestOrderActivity.this;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("程序异常，请联系管理员，异常原因：");
                            stringBuilder2.append(exception.getMessage());
                            Toast.makeText((Context)spareRequestOrderActivity, stringBuilder2.toString(), Toast.LENGTH_LONG).show();
                        } finally {}
                        SpareRequestOrderActivity.this.progressDialogUtil.dismiss();
                        SpareRequestOrderActivity.this.etBarcode.getText().clear();
                        return false;
                    }
                    return false;
                }
            });
            this.lvSpare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Intent intent = new Intent((Context)SpareRequestOrderActivity.this, SpareBarcodeListActivity.class);
                        intent.putExtra("item_index", param1Int);
                        intent.putExtra("spare_barcode_list", (Serializable)SpareRequestOrderActivity.this.spare_barcode_list);
                        SpareRequestOrderActivity.this.startActivityForResult(intent, 0);
                        return;
                    } catch (Exception exception) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append(SpareRequestOrderActivity.this.getClass().getName());
                        stringBuilder1.append(" onCreate: ");
                        stringBuilder1.append(exception.getMessage());
                        Log.e("SystemError", stringBuilder1.toString());
                        SpareRequestOrderActivity spareRequestOrderActivity = SpareRequestOrderActivity.this;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("程序异常，请联系管理员，异常原因：");
                        stringBuilder2.append(exception.getMessage());
                        Toast.makeText((Context)spareRequestOrderActivity, stringBuilder2.toString(), Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            });
            this.adapter_listViewSpare = new ListViewSpareAdapter((Context)this, 2131427414, this.spare_barcode_list.getItems(), 0);
            this.lvSpare.setAdapter((ListAdapter)this.adapter_listViewSpare);
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
            if (this.etRequestUser.getText().toString().equals("")) {
                Toast.makeText((Context)this, "请填写领料人后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            if (this.spare_barcode_list.getBarcodes().size() == 0) {
                Toast.makeText((Context)this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            SaveSpareRequestIn saveSpareRequestIn = new SaveSpareRequestIn();
            saveSpareRequestIn.setUserId(LoginUserInfo.getUserId());
            saveSpareRequestIn.setDeviceCode(SystemInfo.getDeviceCode());
            SWRequestOrderHead sWRequestOrderHead = new SWRequestOrderHead();
            sWRequestOrderHead.setOrderCode(this.etOrderCode.getText().toString());
            sWRequestOrderHead.setRequestUser(this.etRequestUser.getText().toString());
            sWRequestOrderHead.setStatus(0);
            saveSpareRequestIn.setHead(sWRequestOrderHead);
            for (SWSpareBarcode sWSpareBarcode : this.spare_barcode_list.getBarcodes()) {
                BaseOrderBarcode baseOrderBarcode = new BaseOrderBarcode();
                baseOrderBarcode.setAllocationId(sWSpareBarcode.getAllocationId());
                baseOrderBarcode.setBarcode(sWSpareBarcode.getBarcode());
                baseOrderBarcode.setQuantity(sWSpareBarcode.getChangQuantity());
                baseOrderBarcode.setSpareId(sWSpareBarcode.getSpareId());
                baseOrderBarcode.setPcs(sWSpareBarcode.getPcs());
                baseOrderBarcode.setSurplusQuantity(sWSpareBarcode.getSurplusQuantity());
                baseOrderBarcode.setUnit(sWSpareBarcode.getUnit());
                baseOrderBarcode.setPrice(sWSpareBarcode.getPrice());
                baseOrderBarcode.setBrand(sWSpareBarcode.getBrand());
                saveSpareRequestIn.getBarcodes().add(baseOrderBarcode);
            }
            BaseWSOut baseWSOut = XFrameworkWebServiceUtil.API_SaveSpareRequestOrder(saveSpareRequestIn);
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
}


/* Location:              C:\test\dex2jar-2.0\classes2-dex2jar.jar!\com\xframework\xframeworkapp\SpareRequestOrderActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */