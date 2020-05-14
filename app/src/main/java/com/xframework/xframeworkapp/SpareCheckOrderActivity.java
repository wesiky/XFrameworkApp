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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.xframework.adapter.ListViewSpareAdapter;
import com.xframework.model.BaseAllocation;
import com.xframework.model.BaseData;
import com.xframework.model.BaseOrderBarcode;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.SWSpareBarcode;
import com.xframework.model.SpareBarcodeList;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.BaseWSOut;
import com.xframework.model.WS.CheckSpareBarcodeOut;
import com.xframework.model.WS.GetOrderCodeIn;
import com.xframework.model.WS.GetOrderCodeOut;
import com.xframework.model.WS.SaveSpareCheckIn;
import com.xframework.util.ChintWebServiceUtil;
import com.xframework.util.EditTextHelper;
import com.xframework.util.ProgressDialogUtil;
import com.xframework.util.XFrameworkWebServiceUtil;
import java.io.Serializable;

public class SpareCheckOrderActivity extends AppCompatActivity {
    ListViewSpareAdapter adapter_listViewSpare;

    BaseAllocation allocation;

    EditText etAllocation;

    EditText etBarcode;

    ListView lvSpare;

    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();

    SpareBarcodeList spare_barcode_list = new SpareBarcodeList();

    TextView tvOrderCode;

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
            setContentView(R.layout.activity_spare_check_order);
            this.tvOrderCode = (TextView)findViewById(R.id.tvOrderCode);
            this.etBarcode = (EditText)findViewById(R.id.etBarcode);
            this.etAllocation = (EditText)findViewById(R.id.etAllocation);
            this.lvSpare = (ListView)findViewById(R.id.lvSpare);
            TextView textView = new TextView((Context)this);
            textView.setLayoutParams((ViewGroup.LayoutParams)new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setText("未扫描数据");
            textView.setGravity(17);
            textView.setVisibility(View.GONE);
            ((ViewGroup)this.lvSpare.getParent()).addView((View)textView);
            this.lvSpare.setEmptyView((View)textView);
            EditTextHelper.CloseKeyBoard(this.etAllocation);
            EditTextHelper.CloseKeyBoard(this.etBarcode);
            GetOrderCodeIn getOrderCodeIn = new GetOrderCodeIn();
            getOrderCodeIn.setUserId(LoginUserInfo.getUserId());
            getOrderCodeIn.setType(4);
            getOrderCodeIn.setDeviceCode(SystemInfo.getDeviceCode());
            GetOrderCodeOut getOrderCodeOut = XFrameworkWebServiceUtil.API_GetSpareOrderCode(getOrderCodeIn);
            if (getOrderCodeOut.getStatus() == 0) {
                this.tvOrderCode.setText(getOrderCodeOut.getOrderCode());
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getOrderCodeOut.getStatus());
                stringBuilder.append(":");
                stringBuilder.append(getOrderCodeOut.getMsg());
                Toast.makeText((Context)this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                finish();
            }
            this.etAllocation.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                    if (param1KeyEvent.getKeyCode() == 66 && param1KeyEvent.getAction() == 1 && !SpareCheckOrderActivity.this.etAllocation.getText().toString().equals(""))
                        try {
                            String str = SpareCheckOrderActivity.this.etAllocation.getText().toString().replace("\n", "");
                            param1Int = BaseData.containsAllocation(str);
                            if (param1Int >= 0) {
                                SpareCheckOrderActivity.this.allocation = BaseData.getAllocations().get(param1Int);
                                SpareCheckOrderActivity.this.etAllocation.setText(str);
                                SpareCheckOrderActivity.this.etBarcode.requestFocus();
                            } else {
                                Toast.makeText((Context)SpareCheckOrderActivity.this, "货位不存在，请扫描正确货位条码", Toast.LENGTH_LONG).show();
                                if (SpareCheckOrderActivity.this.allocation == null) {
                                    SpareCheckOrderActivity.this.etAllocation.setText("");
                                } else {
                                    SpareCheckOrderActivity.this.etAllocation.setText(SpareCheckOrderActivity.this.allocation.getAllocationCode());
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
                    if (param1KeyEvent.getKeyCode() == 66 && param1KeyEvent.getAction() == 1 && !SpareCheckOrderActivity.this.etBarcode.getText().toString().equals("")) {
                        try {
                            SpareCheckOrderActivity.this.progressDialogUtil.showProgressDialog((Context)SpareCheckOrderActivity.this);
                            if (SpareCheckOrderActivity.this.allocation == null) {
                                Toast.makeText((Context)SpareCheckOrderActivity.this, "请先扫描货位", Toast.LENGTH_LONG).show();
                            } else {
                                SpareCheckOrderActivity spareCheckOrderActivity;
                                final String barcode = SpareCheckOrderActivity.this.etBarcode.getText().toString().replace("\n", "");
                                param1Int = SpareCheckOrderActivity.this.spare_barcode_list.findBarcode(barcode);
                                if (param1Int >= 0) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder((Context)SpareCheckOrderActivity.this);
                                    AlertDialog.Builder builder2 = builder1.setTitle("请选择");
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("条码");
                                    stringBuilder.append(barcode);
                                    stringBuilder.append("已扫描，要删除吗？");
                                    builder2.setMessage(stringBuilder.toString());
                                    builder1.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                            spare_barcode_list.removeBarcode(barcode);
                                            adapter_listViewSpare = new ListViewSpareAdapter(SpareCheckOrderActivity.this, R.layout.item_spare, spare_barcode_list.getItems(), 0);
                                            lvSpare.setAdapter((ListAdapter)SpareCheckOrderActivity.this.adapter_listViewSpare);
                                            SpareCheckOrderActivity spareCheckOrderActivity = SpareCheckOrderActivity.this;
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append("条码");
                                            stringBuilder.append(barcode);
                                            stringBuilder.append("已删除");
                                            Toast.makeText((Context)spareCheckOrderActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    builder1.setNeutralButton("否", null);
                                    builder1.show();
                                    SpareCheckOrderActivity.this.progressDialogUtil.dismiss();
                                    SpareCheckOrderActivity.this.etBarcode.getText().clear();
                                    return false;
                                }
                                CheckSpareBarcodeOut checkSpareBarcodeOut = ChintWebServiceUtil.GetSpareBarcode(barcode);
                                if (checkSpareBarcodeOut.getStatus() == 0) {
                                    if (checkSpareBarcodeOut.getBarcode().getSurplusQuantity() <= 0.0F) {
                                        spareCheckOrderActivity = SpareCheckOrderActivity.this;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("条码");
                                        stringBuilder.append(SpareCheckOrderActivity.this.etBarcode.getText());
                                        stringBuilder.append("扫码失败：条码已消耗");
                                        Toast.makeText((Context)spareCheckOrderActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                    } else {
                                        SWSpareBarcode sWSpareBarcode = checkSpareBarcodeOut.getBarcode();
                                        sWSpareBarcode.setChangQuantity(sWSpareBarcode.getSurplusQuantity());
                                        sWSpareBarcode.setAllocationId(SpareCheckOrderActivity.this.allocation.getAllocationId());
                                        SpareCheckOrderActivity.this.spare_barcode_list.addBarocde(sWSpareBarcode);
                                        SpareCheckOrderActivity.this.adapter_listViewSpare.notifyDataSetChanged();
                                        spareCheckOrderActivity = SpareCheckOrderActivity.this;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("条码");
                                        stringBuilder.append(SpareCheckOrderActivity.this.etBarcode.getText());
                                        stringBuilder.append("扫码成功");
                                        Toast.makeText((Context)spareCheckOrderActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    SpareCheckOrderActivity spareCheckOrderActivity1 = SpareCheckOrderActivity.this;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("条码");
                                    stringBuilder.append(SpareCheckOrderActivity.this.etBarcode.getText());
                                    stringBuilder.append("扫码失败：");
                                    stringBuilder.append(checkSpareBarcodeOut.getStatus());
                                    stringBuilder.append(":");
                                    stringBuilder.append(checkSpareBarcodeOut.getMsg());
                                    Toast.makeText((Context)spareCheckOrderActivity1, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (Exception exception) {

                        } finally {}
                        SpareCheckOrderActivity.this.progressDialogUtil.dismiss();
                        SpareCheckOrderActivity.this.etBarcode.getText().clear();
                        return false;
                    }
                    return false;
                }
            });
            this.lvSpare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Intent intent = new Intent((Context)SpareCheckOrderActivity.this, SpareReceivedBarcodeListActivity.class);
                        intent.putExtra("item_index", param1Int);
                        intent.putExtra("spare_barcode_list", (Serializable)SpareCheckOrderActivity.this.spare_barcode_list);
                        SpareCheckOrderActivity.this.startActivityForResult(intent, 0);
                        return;
                    } catch (Exception exception) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append(SpareCheckOrderActivity.this.getClass().getName());
                        stringBuilder1.append(" onCreate: ");
                        stringBuilder1.append(exception.getMessage());
                        Log.e("SystemError", stringBuilder1.toString());
                        SpareCheckOrderActivity spareCheckOrderActivity = SpareCheckOrderActivity.this;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("程序异常，请联系管理员，异常原因：");
                        stringBuilder2.append(exception.getMessage());
                        Toast.makeText((Context)spareCheckOrderActivity, stringBuilder2.toString(), Toast.LENGTH_LONG).show();
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
            if (this.spare_barcode_list.getBarcodes().size() == 0) {
                Toast.makeText((Context)this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            SaveSpareCheckIn saveSpareCheckIn = new SaveSpareCheckIn();
            saveSpareCheckIn.setUserId(LoginUserInfo.getUserId());
            saveSpareCheckIn.setDeviceCode(SystemInfo.getDeviceCode());
            saveSpareCheckIn.setOrderCode(this.tvOrderCode.getText().toString());
            for (SWSpareBarcode sWSpareBarcode : this.spare_barcode_list.getBarcodes()) {
                BaseOrderBarcode baseOrderBarcode = new BaseOrderBarcode();
                baseOrderBarcode.setAllocationId(sWSpareBarcode.getAllocationId());
                baseOrderBarcode.setBarcode(sWSpareBarcode.getBarcode());
                baseOrderBarcode.setQuantity(sWSpareBarcode.getSurplusQuantity());
                baseOrderBarcode.setSpareId(sWSpareBarcode.getSpareId());
                baseOrderBarcode.setPcs(sWSpareBarcode.getPcs());
                baseOrderBarcode.setSurplusQuantity(sWSpareBarcode.getSurplusQuantity());
                baseOrderBarcode.setUnit(sWSpareBarcode.getUnit());
                baseOrderBarcode.setPrice(sWSpareBarcode.getPrice());
                baseOrderBarcode.setBrand(sWSpareBarcode.getBrand());
                saveSpareCheckIn.getBarcodes().add(baseOrderBarcode);
            }
            BaseWSOut baseWSOut = XFrameworkWebServiceUtil.API_SaveSpareCheckOrder(saveSpareCheckIn);
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