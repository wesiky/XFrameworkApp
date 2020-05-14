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
import com.xframework.model.SWReturnOrderHead;
import com.xframework.model.SWSpareBarcode;
import com.xframework.model.SpareBarcodeList;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.BaseWSOut;
import com.xframework.model.WS.CheckSpareBarcodeOut;
import com.xframework.model.WS.GetOrderCodeIn;
import com.xframework.model.WS.GetOrderCodeOut;
import com.xframework.model.WS.SaveSpareReturnIn;
import com.xframework.util.ChintWebServiceUtil;
import com.xframework.util.EditTextHelper;
import com.xframework.util.ProgressDialogUtil;
import com.xframework.util.XFrameworkWebServiceUtil;
import java.io.Serializable;

public class SpareReturnOrderActivity extends AppCompatActivity {
    ListViewSpareAdapter adapter_listViewSpare;

    BaseAllocation allocation;

    EditText etAllocation;

    EditText etBarcode;

    TextView etOrderCode;

    EditText etReturnUser;

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
            setContentView(R.layout.activity_spare_return_order);
            this.etOrderCode = (TextView)findViewById(R.id.etOrderCode);
            this.etReturnUser = (EditText)findViewById(R.id.etReturnUser);
            this.etBarcode = (EditText)findViewById(R.id.etBarcode);
            this.etAllocation = (EditText)findViewById(R.id.etAllocation);
            GetOrderCodeIn getOrderCodeIn = new GetOrderCodeIn();
            getOrderCodeIn.setUserId(LoginUserInfo.getUserId());
            getOrderCodeIn.setType(2);
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
            EditTextHelper.CloseKeyBoard(this.etAllocation);
            EditTextHelper.CloseKeyBoard(this.etBarcode);
            EditTextHelper.CloseKeyBoard(this.etReturnUser);
            this.etReturnUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View param1View, boolean param1Boolean) {
                    AlertDialog.Builder builder = new AlertDialog.Builder((Context)SpareReturnOrderActivity.this);
                    builder.setTitle("请选择");
                    final String[] repairmans = new String[BaseData.getRepairmans().size()];
                    BaseData.getRepairmans().toArray((Object[])repairmans);
                    builder.setItems((CharSequence[])repairmans, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                            SpareReturnOrderActivity.this.etReturnUser.setText(repairmans[param2Int]);
                            SpareReturnOrderActivity.this.etAllocation.requestFocus();
                        }
                    });
                    builder.show();
                }
            });
            this.etAllocation.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                    if (param1KeyEvent.getKeyCode() == 66 && param1KeyEvent.getAction() == 1 && !SpareReturnOrderActivity.this.etAllocation.getText().toString().equals(""))
                        try {
                            String str = SpareReturnOrderActivity.this.etAllocation.getText().toString().replace("\n", "");
                            param1Int = BaseData.containsAllocation(str);
                            if (param1Int >= 0) {
                                SpareReturnOrderActivity.this.allocation = BaseData.getAllocations().get(param1Int);
                                SpareReturnOrderActivity.this.etAllocation.setText(str);
                                SpareReturnOrderActivity.this.etBarcode.requestFocus();
                            } else {
                                Toast.makeText((Context)SpareReturnOrderActivity.this, "货位不存在，请扫描正确货位条码", Toast.LENGTH_LONG).show();
                                if (SpareReturnOrderActivity.this.allocation == null) {
                                    SpareReturnOrderActivity.this.etAllocation.setText("");
                                } else {
                                    SpareReturnOrderActivity.this.etAllocation.setText(SpareReturnOrderActivity.this.allocation.getAllocationCode());
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
                    if (param1KeyEvent.getKeyCode() == 66 && param1KeyEvent.getAction() == 1 && !SpareReturnOrderActivity.this.etBarcode.getText().toString().equals("")) {
                        try {
                            SpareReturnOrderActivity spareReturnOrderActivity;
                            SpareReturnOrderActivity.this.progressDialogUtil.showProgressDialog((Context)SpareReturnOrderActivity.this);
                            if (SpareReturnOrderActivity.this.allocation == null) {
                                Toast.makeText((Context)SpareReturnOrderActivity.this, "请先扫描货位", Toast.LENGTH_LONG).show();
                                SpareReturnOrderActivity.this.progressDialogUtil.dismiss();
                                SpareReturnOrderActivity.this.etBarcode.getText().clear();
                                return false;
                            }
                            final String barcode = SpareReturnOrderActivity.this.etBarcode.getText().toString().replace("\n", "");
                            param1Int = SpareReturnOrderActivity.this.spare_barcode_list.findBarcode(barcode);
                            if (param1Int >= 0) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder((Context)SpareReturnOrderActivity.this);
                                AlertDialog.Builder builder2 = builder1.setTitle("请选择");
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(barcode);
                                stringBuilder.append("已扫描，要删除吗？");
                                builder2.setMessage(stringBuilder.toString());
                                builder1.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                                        SpareReturnOrderActivity.this.spare_barcode_list.removeBarcode(barcode);
                                        SpareReturnOrderActivity.this.adapter_listViewSpare = new ListViewSpareAdapter((Context)SpareReturnOrderActivity.this, 2131427414, SpareReturnOrderActivity.this.spare_barcode_list.getItems(), 0);
                                        SpareReturnOrderActivity.this.lvSpare.setAdapter((ListAdapter)SpareReturnOrderActivity.this.adapter_listViewSpare);
                                        SpareReturnOrderActivity spareReturnOrderActivity = SpareReturnOrderActivity.this;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("条码");
                                        stringBuilder.append(barcode);
                                        stringBuilder.append("已删除");
                                        Toast.makeText((Context)spareReturnOrderActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                                    }
                                });
                                builder1.setNeutralButton("否", null);
                                builder1.show();
                                SpareReturnOrderActivity.this.progressDialogUtil.dismiss();
                                SpareReturnOrderActivity.this.etBarcode.getText().clear();
                                return false;
                            }
                            CheckSpareBarcodeOut checkSpareBarcodeOut = ChintWebServiceUtil.GetSpareBarcode(barcode);
                            if (checkSpareBarcodeOut.getStatus() == 0) {
                                SWSpareBarcode sWSpareBarcode = checkSpareBarcodeOut.getBarcode();
                                sWSpareBarcode.setAllocationId(SpareReturnOrderActivity.this.allocation.getAllocationId());
                                sWSpareBarcode.setChangQuantity(1.0F);
                                SpareReturnOrderActivity.this.spare_barcode_list.addBarocde(sWSpareBarcode);
                                SpareReturnOrderActivity.this.adapter_listViewSpare.notifyDataSetChanged();
                                spareReturnOrderActivity = SpareReturnOrderActivity.this;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(SpareReturnOrderActivity.this.etBarcode.getText());
                                stringBuilder.append("扫码成功");
                                Toast.makeText((Context)spareReturnOrderActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                            } else {
                                SpareReturnOrderActivity spareReturnOrderActivity1 = SpareReturnOrderActivity.this;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("条码");
                                stringBuilder.append(SpareReturnOrderActivity.this.etBarcode.getText());
                                stringBuilder.append("扫码失败：");
                                stringBuilder.append(checkSpareBarcodeOut.getStatus());
                                stringBuilder.append(":");
                                stringBuilder.append(checkSpareBarcodeOut.getMsg());
                                Toast.makeText((Context)spareReturnOrderActivity1, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception exception) {
                            StringBuilder stringBuilder1 = new StringBuilder();
                            stringBuilder1.append(SpareReturnOrderActivity.this.getClass().getName());
                            stringBuilder1.append(" onCreate: ");
                            stringBuilder1.append(exception.getMessage());
                            Log.e("SystemError", stringBuilder1.toString());
                            SpareReturnOrderActivity spareReturnOrderActivity = SpareReturnOrderActivity.this;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("程序异常，请联系管理员，异常原因：");
                            stringBuilder2.append(exception.getMessage());
                            Toast.makeText((Context)spareReturnOrderActivity, stringBuilder2.toString(), Toast.LENGTH_LONG).show();
                        } finally {}
                        SpareReturnOrderActivity.this.progressDialogUtil.dismiss();
                        SpareReturnOrderActivity.this.etBarcode.getText().clear();
                        return false;
                    }
                    return false;
                }
            });
            this.lvSpare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                    try {
                        Intent intent = new Intent((Context)SpareReturnOrderActivity.this, SpareBarcodeListActivity.class);
                        intent.putExtra("item_index", param1Int);
                        intent.putExtra("spare_barcode_list", (Serializable)SpareReturnOrderActivity.this.spare_barcode_list);
                        SpareReturnOrderActivity.this.startActivityForResult(intent, 0);
                        return;
                    } catch (Exception exception) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append(SpareReturnOrderActivity.this.getClass().getName());
                        stringBuilder1.append(" onCreate: ");
                        stringBuilder1.append(exception.getMessage());
                        Log.e("SystemError", stringBuilder1.toString());
                        SpareReturnOrderActivity spareReturnOrderActivity = SpareReturnOrderActivity.this;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("程序异常，请联系管理员，异常原因：");
                        stringBuilder2.append(exception.getMessage());
                        Toast.makeText((Context)spareReturnOrderActivity, stringBuilder2.toString(), Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            });
            this.adapter_listViewSpare = new ListViewSpareAdapter((Context)this, R.layout.item_spare, this.spare_barcode_list.getItems(), 0);
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
            if (this.etReturnUser.getText().toString().equals("")) {
                Toast.makeText((Context)this, "请填写领料人后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            if (this.spare_barcode_list.getBarcodes().size() == 0) {
                Toast.makeText((Context)this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
                return;
            }
            SaveSpareReturnIn saveSpareReturnIn = new SaveSpareReturnIn();
            saveSpareReturnIn.setUserId(LoginUserInfo.getUserId());
            saveSpareReturnIn.setDeviceCode(SystemInfo.getDeviceCode());
            SWReturnOrderHead sWReturnOrderHead = new SWReturnOrderHead();
            sWReturnOrderHead.setOrderCode(this.etOrderCode.getText().toString());
            sWReturnOrderHead.setReturnUser(this.etReturnUser.getText().toString());
            sWReturnOrderHead.setStatus(0);
            saveSpareReturnIn.setHead(sWReturnOrderHead);
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
                saveSpareReturnIn.getBarcodes().add(baseOrderBarcode);
            }
            BaseWSOut baseWSOut = XFrameworkWebServiceUtil.API_SaveSpareReturnOrder(saveSpareReturnIn);
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
}


/* Location:              C:\test\dex2jar-2.0\classes2-dex2jar.jar!\com\xframework\xframeworkapp\SpareReturnOrderActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */