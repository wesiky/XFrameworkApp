package com.xframework.xframeworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xframework.adapter.ProductAdapter;
import com.xframework.model.BaseAllocation;
import com.xframework.model.BaseData;
import com.xframework.model.ListItem;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.PWProductBarcode;
import com.xframework.model.PWReceivedOrderDetail;
import com.xframework.model.ProductBarcodeList;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.CheckOrderReceivedBarcodeIn;
import com.xframework.model.WS.CheckOrderReceivedBarcodeOut;
import com.xframework.model.WS.GetOrderCodeIn;
import com.xframework.model.WS.GetOrderCodeOut;
import com.xframework.model.WS.SaveReceivedOrderIn;
import com.xframework.model.WS.SaveReceivedOrderOut;
import com.xframework.util.ProgressDialogUtil;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.util.Date;
import java.util.List;

public class OrderReceivedActivity extends AppCompatActivity {

    TextView tvAllocation;
    Spinner spType;
    TextView tvOrderCode;
    TextView tvOrderRemarkValue;
    EditText etBarcode;
    ProductBarcodeList product_barcode_list = new ProductBarcodeList();
    ProductAdapter adapter_product;
    ListView lvProduct;
    BaseAllocation allocation;

    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_received);

        tvOrderCode = findViewById(R.id.etOrderCode);
        tvOrderRemarkValue = findViewById(R.id.tvOrderRemarkValue);
        etBarcode = findViewById(R.id.etBarcode);
        tvAllocation = findViewById(R.id.tvAllocation);
        spType = findViewById(R.id.spType);
        lvProduct = findViewById(R.id.lvBarcode);

        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));
        emptyView.setText("未扫描产品数据");
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)lvProduct.getParent()).addView(emptyView);
        lvProduct.setEmptyView(emptyView);

        //禁止键盘弹出
        etBarcode.setInputType(InputType.TYPE_NULL);

        //加载出库类型
        List<ListItem> data_received_type = BaseData.getSet("ReceivedType");
        ArrayAdapter<ListItem> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_received_type);
        spType.setAdapter(adapter);

        //填充单号
        GetOrderCodeIn in = new GetOrderCodeIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setType(3);
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetOrderCodeOut ws_out = XFrameworkWebServiceUtil.API_GetOrderCode(in);
        if (ws_out.getStatus() == 0) {
            tvOrderCode.setText(ws_out.getOrderCode());
        } else {
            //单号获取失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
        }

        int index = BaseData.containsAllocation(tvAllocation.getText().toString());
        if (index >= 0) {
            //设置默认货位
            allocation = BaseData.getAllocations().get(index);
        } else {
            //货位不存在，提示对应信息
            Toast.makeText(OrderReceivedActivity.this, "默认货位设置有误，请联系管理员", Toast.LENGTH_LONG).show();
        }

        //仓库调整为一个货位，去除扫码事件 2019-08-13
//        //设置货位扫码事件
//        etAllocation.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (i == KeyEvent.KEYCODE_ENTER && !etAllocation.getText().toString().equals("")) {
//                    try {
//                        int len = etAllocation.getText().toString().length();
//                        //获取新货位
//                        String allocationCodeNew = etAllocation.getText().toString().substring(0, len - allocationLen);
//                        //获取旧货位
//                        String allocationCodeOld = etAllocation.getText().toString().substring(len - allocationLen, len);
//                        if(!allocationCodeNew.equals("")) {
//                            //校验货位是否存在
//                            int index = BaseData.containsAllocation(allocationCodeNew);
//                            if (index >= 0) {
//                                //货位存在，更新仓库信息和货位信息
//                                if (allocation == null) {
//                                    allocation = BaseData.getAllocations().get(index);
//                                    etAllocation.setText(allocationCodeNew);
//                                    allocationLen = allocationCodeNew.length();
//                                } else if (allocation.getIntermediateWarehouseId() == BaseData.getAllocations().get(index).getIntermediateWarehouseId()) {
//                                    etAllocation.setText(allocationCodeNew);
//                                    allocationLen = allocationCodeNew.length();
//                                } else {
//                                    //货位非本仓库，提示对应信息
//                                    Toast.makeText(OrderReceivedActivity.this, "非本仓库货位，请扫描正确货位条码", Toast.LENGTH_LONG).show();
//                                    etAllocation.setText(allocationCodeOld);
//                                }
//                            } else {
//                                //货位不存在，提示对应信息
//                                Toast.makeText(OrderReceivedActivity.this, "货位不存在，请扫描正确货位条码", Toast.LENGTH_LONG).show();
//                                etAllocation.setText(allocationCodeOld);
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                return true;
//            }
//        });

        //设置条码扫码事件
        etBarcode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && !etBarcode.getText().toString().equals("")) {
                    try {
                        progressDialogUtil.showProgressDialog(OrderReceivedActivity.this);
                        final String barcode = etBarcode.getText().toString();
                        //校验条码
                        CheckOrderReceivedBarcodeIn in = new CheckOrderReceivedBarcodeIn();
                        in.setUserId(LoginUserInfo.getUserId());
                        in.setBarcode(barcode);
                        in.setDeviceCode(SystemInfo.getDeviceCode());
                        CheckOrderReceivedBarcodeOut ws_out = XFrameworkWebServiceUtil.API_CheckReceivedOrderBarcode(in);
                        if (ws_out.getStatus() == 0) {
                            product_barcode_list.clear();
                            for (PWProductBarcode product_barcode : ws_out.getBarcodes()) {
                                    product_barcode_list.addBarocde(product_barcode);
                            }
                            adapter_product.notifyDataSetChanged();
                            tvOrderRemarkValue.setText(ws_out.getDescription());
                            Toast.makeText(OrderReceivedActivity.this, "条码" + etBarcode.getText() + "扫码成功", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(OrderReceivedActivity.this, "条码" + etBarcode.getText() + "扫码失败：" + ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        etBarcode.getText().clear();
                        progressDialogUtil.dismiss();
                    }
                }
                return true;
            }
        });

        //设置产品清单点击事件
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(OrderReceivedActivity.this, BarcodeListActivity.class);
                intent.putExtra("item_index", i);
                intent.putExtra("product_barcode_list", product_barcode_list);
                startActivityForResult(intent, 0);
            }
        });

        //设置产品清单
        adapter_product = new ProductAdapter(this, R.layout.item_product, product_barcode_list.getItems());
        lvProduct.setAdapter(adapter_product);
    }

    //结果处理函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            product_barcode_list = (ProductBarcodeList) data.getSerializableExtra("product_barcode_list");
            adapter_product = new ProductAdapter(this, R.layout.item_product, product_barcode_list.getItems());
            lvProduct.setAdapter(adapter_product);
        }
    }

    public void returnOnClick(View v) {
        this.finish();
    }

    public void saveOnClick(View v) {
        if (allocation == null) {
            Toast.makeText(this, "货位为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
            return;
        }
        if (product_barcode_list.getBarcodes().size() == 0) {
            Toast.makeText(this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
            return;
        }
        SaveReceivedOrderIn ws_in = new SaveReceivedOrderIn();

        //设置基础数据
        ws_in.setUserId(LoginUserInfo.getUserId());
        ws_in.setDeviceCode(SystemInfo.getDeviceCode());
        ws_in.setOptionType(0);

        //设置表头
        ws_in.getHead().setIntermediateWarehouseId(allocation.getIntermediateWarehouseId());
        ws_in.getHead().setOrderCode(tvOrderCode.getText().toString());
        ws_in.getHead().setType(Integer.valueOf(((ListItem) spType.getSelectedItem()).getValue()));
        ws_in.getHead().setCreateDate(new Date());
        ws_in.getHead().setCreateUser(LoginUserInfo.getUserName());
        ws_in.getHead().setLastUpdateDate(new Date());
        ws_in.getHead().setLastUpdateUser(LoginUserInfo.getUserName());

        //设置表体
        for (PWProductBarcode barcode : product_barcode_list.getBarcodes()) {
            PWReceivedOrderDetail detail = new PWReceivedOrderDetail();
            detail.setAllocationId(allocation.getAllocationId());
            detail.setOrderCode(ws_in.getHead().getOrderCode());
            detail.setOuterBarcode(barcode.getOuterBarcode());
            detail.setProductBarcode(barcode.getBarcode());
            detail.setProductId(barcode.getProductId());
            detail.setCreateDate(new Date());
            detail.setCreateUser(LoginUserInfo.getUserName());
            detail.setLastUpdateDate(new Date());
            detail.setLastUpdateUser(LoginUserInfo.getUserName());
            ws_in.getBody().add(detail);
        }

        //保存单据
        SaveReceivedOrderOut ws_out = XFrameworkWebServiceUtil.API_SaveReceivedOrder(ws_in);

        Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
        //关闭本页
        if (ws_out.getStatus() == 0) {
            this.finish();
        }
    }
}
