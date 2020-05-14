package com.xframework.xframeworkapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.adapter.BatchProductAdapter;
import com.xframework.delegate.BatchDeliveryBarcodeDelegate;
import com.xframework.item.BatchProductItem;
import com.xframework.model.BaseData;
import com.xframework.model.BaseIntermediateWarehouse;
import com.xframework.model.BatchProductBarcodeList;
import com.xframework.model.ListItem;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.MrpSapOrder;
import com.xframework.model.PWDeliveryOrderDetail;
import com.xframework.model.PWProductBarcode;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.CheckBatchOrderBarcodeIn;
import com.xframework.model.WS.CheckBatchOrderBarcodeOut;
import com.xframework.model.WS.CheckFrameBarcodeIn;
import com.xframework.model.WS.CheckFrameBarcodeOut;
import com.xframework.model.WS.DeliveryBatchBodyIn;
import com.xframework.model.WS.DeliveryBatchBodyOut;
import com.xframework.model.WS.GetBatchOrderInfoIn;
import com.xframework.model.WS.GetBatchOrderInfoOut;
import com.xframework.model.WS.GetOrderCodeIn;
import com.xframework.model.WS.GetOrderCodeOut;
import com.xframework.model.WS.SaveDeliveryBatchOrderIn;
import com.xframework.model.WS.SaveDeliveryBatchOrderOut;
import com.xframework.util.ProgressDialogUtil;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchDeliveryActivity extends AppCompatActivity  implements BatchDeliveryBarcodeDelegate{

    private String orderCode = "";
    private TextView tvBatchNo;
    private EditText etFrameBarcode;
    private EditText etBarcode;
    private RecyclerView rvProductList;
    private Spinner spWarehouse;
    private BatchProductAdapter sectionAdapter;
    Intent intent;
    int frameBarcodeLen = 0;
    ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil();


    BatchProductBarcodeList batch_product_barcode_list = new BatchProductBarcodeList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_delivery);

        tvBatchNo = findViewById(R.id.tvBatchNo);
        etFrameBarcode = findViewById(R.id.etFrameBarcode);
        etBarcode = findViewById(R.id.etBarcode);
        spWarehouse = findViewById(R.id.spWarehouse);
        rvProductList = findViewById(R.id.rvProductList);

        intent = getIntent();

        //禁止键盘弹出
        etFrameBarcode.setInputType(InputType.TYPE_NULL);
        etBarcode.setInputType(InputType.TYPE_NULL);

        //填充单号
        GetOrderCodeIn in = new GetOrderCodeIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setType(1);
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetOrderCodeOut ws_out = XFrameworkWebServiceUtil.API_GetOrderCode(in);
        if (ws_out.getStatus() == 0) {
            orderCode = ws_out.getOrderCode();
        } else {
            //单号获取失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
        }

        //加载仓库数据
        List<ListItem> data_warehouse = new ArrayList<>();
        for (BaseIntermediateWarehouse intermediate_warehouse : BaseData.getIntermediateWarehouse()) {
            data_warehouse.add(new ListItem(intermediate_warehouse.getIntermediateWarehouseName(), String.valueOf(intermediate_warehouse.getIntermediateWarehouseId())));
        }
        ArrayAdapter<ListItem> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, data_warehouse);
        spWarehouse.setAdapter(adapter);

        etFrameBarcode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && !etFrameBarcode.getText().toString().equals("")) {
                    try {
                        progressDialogUtil.showProgressDialog(BatchDeliveryActivity.this);
                        int len = etFrameBarcode.getText().toString().length();
                        //获取新货位
                        String frameBarcodeNew = etFrameBarcode.getText().toString().substring(0, len - frameBarcodeLen);
                        //获取旧货位
                        String frameBarcodeOld = etFrameBarcode.getText().toString().substring(len - frameBarcodeLen, len);
                        //校验条码数据
                        CheckFrameBarcodeIn in = new CheckFrameBarcodeIn();
                        in.setUserId(LoginUserInfo.getUserId());
                        in.setBarcode(frameBarcodeNew);
                        in.setDeviceCode(SystemInfo.getDeviceCode());
                        CheckFrameBarcodeOut ws_out = XFrameworkWebServiceUtil.API_CheckFrameBarcode(in);
                        if (ws_out.getStatus() == 0) {
                            etFrameBarcode.setText(frameBarcodeNew);
                            Toast.makeText(BatchDeliveryActivity.this, "物料框条码" + etFrameBarcode.getText() + "扫码成功", Toast.LENGTH_LONG).show();
                        } else {
                            etFrameBarcode.setText(frameBarcodeOld);
                            Toast.makeText(BatchDeliveryActivity.this, "物料框条码" + etFrameBarcode.getText() + "扫码失败：" + ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        //设置扫码事件
        etBarcode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && !etBarcode.getText().toString().equals("")) {
                    try {
                        final String barcode = etBarcode.getText().toString();
                        //非外箱进行条码验证
                        if (!barcode.startsWith("0")) {
                            if (batch_product_barcode_list.findBarcode(barcode) >= 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BatchDeliveryActivity.this);
                                builder.setTitle("请选择").setMessage("条码" + barcode + "已扫描，要删除吗？");
                                builder.setPositiveButton("是",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        batch_product_barcode_list.removeBarcode(barcode);
                                        rvProductList.notify();
                                        Toast.makeText(BatchDeliveryActivity.this, "条码" + barcode + "已删除",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNeutralButton("否",null);
                                builder.show();
                                return true;
                            }
                        }
                        //填充产品数据
                        CheckBatchOrderBarcodeIn in = new CheckBatchOrderBarcodeIn();
                        in.setUserId(LoginUserInfo.getUserId());
                        int warehouse_id = Integer.valueOf(((ListItem) spWarehouse.getSelectedItem()).getValue());
                        in.setWarehouseId(warehouse_id);
                        in.setBarcode(barcode);
                        in.setDeviceCode(SystemInfo.getDeviceCode());
                        CheckBatchOrderBarcodeOut ws_out = XFrameworkWebServiceUtil.API_CheckBatchOrderBarcode(in);
                        if (ws_out.getStatus() == 0) {
                            if(ws_out.getBarcodes().size()>0) {
                                if(batch_product_barcode_list.findItem(ws_out.getBarcodes().get(0).getProductCode())<0)
                                {
                                    Toast.makeText(BatchDeliveryActivity.this, "条码" + etBarcode.getText() + "扫码失败：非本批次物料", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    for (PWProductBarcode product_barcode : ws_out.getBarcodes()) {
                                        if (batch_product_barcode_list.findBarcode(product_barcode.getBarcode()) < 0) {
                                            batch_product_barcode_list.addBarocde(product_barcode);
                                        }
                                    }
                                }
                                sectionAdapter.notifyDataSetChanged();
                                Toast.makeText(BatchDeliveryActivity.this, "条码" + etBarcode.getText() + "扫码成功", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(BatchDeliveryActivity.this, "条码" + etBarcode.getText() + "扫码失败：" + ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        etBarcode.getText().clear();
                    }
                }
                return true;
            }
        });

//        //设置产品清单点击事件
//        rvProductList.setOn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(DeliveryActivity.this, BarcodeListActivity.class);
//                intent.putExtra("item_index",i);
//                intent.putExtra("product_barcode_list",product_barcode_list);
//                startActivityForResult(intent,0);
//            }
//        });


        initData();
        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));
        sectionAdapter = new BatchProductAdapter(this,R.layout.item_batch_info, batch_product_barcode_list.getItems(),this);
        rvProductList.setAdapter(sectionAdapter);
    }

    private void initData()
    {
        String batchNo = intent.getStringExtra("batch_no");
        tvBatchNo.setText(batchNo);
        GetBatchOrderInfoIn in = new GetBatchOrderInfoIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setBatchNo(batchNo);
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetBatchOrderInfoOut ws_out = XFrameworkWebServiceUtil.API_GetBatchOrderInfo(in);
        if (ws_out.getStatus() == 0) {
            ArrayList<MrpSapOrder> order_list = ws_out.getOrderList();
            ArrayList<BatchProductItem> items = new ArrayList<>();
            for(MrpSapOrder order:order_list)
            {
                BatchProductItem item = new BatchProductItem();
                item.setProductCode(order.getProductCode());
                item.setProductName(order.getProductName());
                item.setBodyProductId(order.getProductBodyId());
                item.setBodyProductCode(order.getProductBodyCode());
                item.setBodyProductName(order.getProductBodyName());
                item.setBatchQuantity(order.getCount());
                item.setAdd(false);
                items.add(item);
            }
            batch_product_barcode_list.initProductItem(items);
        } else {
            //单号获取失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,intent);
            this.finish();
        }
    }


    public void returnOnClick(View v) {

        intent.putExtra("batchNo", tvBatchNo.getText());
        intent.putExtra("batchData", batch_product_barcode_list);
        setResult(RESULT_OK, intent);
        this.finish();
    }

    //结果处理函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            batch_product_barcode_list = (BatchProductBarcodeList) data.getSerializableExtra("batch_product_barcode_list");
            sectionAdapter = new BatchProductAdapter(this,R.layout.item_batch_info, batch_product_barcode_list.getItems(),this);
            rvProductList.setAdapter(sectionAdapter);
        }
    }

    public void saveOnClick(View v) {
        if(etFrameBarcode.getText().toString().equals(""))
        {
            Toast.makeText(this, "物料框条码为空，请扫描", Toast.LENGTH_LONG).show();
            return ;
        }
        if (batch_product_barcode_list.getBatchCount() < batch_product_barcode_list.getBarcodes().size()) {
            Toast.makeText(this, "条码数量超过批单数量，请校对！", Toast.LENGTH_LONG).show();
            return;
        }
        DeliveryBatchBodyIn ws_in = new DeliveryBatchBodyIn ();

        //设置基础数据
        ws_in.setUserId(LoginUserInfo.getUserId());
        ws_in.setDeviceCode(SystemInfo.getDeviceCode());
        ws_in.setBatchNo(tvBatchNo.getText().toString());
        ws_in.setFrameBarcode(etFrameBarcode.getText().toString());
        ws_in.setOptionType(0);

        //设置表头
        ws_in.getHead().setIntermediateWarehouseId(Integer.valueOf(((ListItem) spWarehouse.getSelectedItem()).getValue()));
        ws_in.getHead().setOrderCode(orderCode);
        ws_in.getHead().setType(1);
        ws_in.getHead().setCreateDate(new Date());
        ws_in.getHead().setCreateUser(LoginUserInfo.getUserName());
        ws_in.getHead().setLastUpdateDate(new Date());
        ws_in.getHead().setLastUpdateUser(LoginUserInfo.getUserName());
        ws_in.getHead().setDescription(tvBatchNo.getText().toString());

        //设置表体
        for (PWProductBarcode barcode : batch_product_barcode_list.getBarcodes()) {
            PWDeliveryOrderDetail detail = new PWDeliveryOrderDetail();
            detail.setAllocationId(barcode.getAllocationId());
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
        DeliveryBatchBodyOut ws_out = XFrameworkWebServiceUtil.API_DeliveryBatchBody(ws_in);

        Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
        //关闭本页
        if (ws_out.getStatus() == 0) {
            setResult(RESULT_OK,intent);
            this.finish();
        }
    }

    @Override
    public void editBarcode(int itemIndex) {
        Intent intent = new Intent(BatchDeliveryActivity.this, BatchBarcodeListActivity.class);
        intent.putExtra("item_index",itemIndex);
        intent.putExtra("batch_product_barcode_list", batch_product_barcode_list);
        startActivityForResult(intent, 0);
    }
}
