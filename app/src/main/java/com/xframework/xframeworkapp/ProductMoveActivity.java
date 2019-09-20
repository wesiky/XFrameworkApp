package com.xframework.xframeworkapp;

import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.xframework.adapter.ProductAdapter;
import com.xframework.model.BaseAllocation;
import com.xframework.model.BaseData;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.PWProductBarcode;
import com.xframework.model.PWProductMoveOrderDetail;
import com.xframework.model.ProductBarcodeList;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.CheckMoveBarcodeIn;
import com.xframework.model.WS.CheckMoveBarcodeOut;
import com.xframework.model.WS.GetOrderCodeIn;
import com.xframework.model.WS.GetOrderCodeOut;
import com.xframework.model.WS.SaveMoveOrderIn;
import com.xframework.model.WS.SaveMoveOrderOut;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.util.Date;

public class ProductMoveActivity extends AppCompatActivity {

    EditText etAllocationIn;
    TextView tvOrderCode;
    EditText etBarcode;
    ProductBarcodeList product_barcode_list = new ProductBarcodeList();
    ProductAdapter adapter_product;
    ListView lvProduct;

    BaseAllocation allocationIn;
    int allocationInLen=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_move);

        tvOrderCode = findViewById(R.id.etOrderCode);
        etBarcode = findViewById(R.id.etBarcode);
        etAllocationIn = findViewById(R.id.etAllocationIn);
        lvProduct = findViewById(R.id.lvBarcode);

        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));
        emptyView.setText("未扫描产品数据或货位");
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)lvProduct.getParent()).addView(emptyView);
        lvProduct.setEmptyView(emptyView);

        //禁止键盘弹出
        etBarcode.setInputType(InputType.TYPE_NULL);
        etAllocationIn.setInputType(InputType.TYPE_NULL);

        //填充单号
        GetOrderCodeIn in = new GetOrderCodeIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setType(0);
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetOrderCodeOut ws_out = XFrameworkWebServiceUtil.API_GetOrderCode(in);
        if (ws_out.getStatus() == 0) {
            tvOrderCode.setText(ws_out.getOrderCode());
        } else {
            //单号获取失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
        }

        //设置移入货位扫码事件
        etAllocationIn.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && !etAllocationIn.getText().toString().equals("")) {
                    try {
                        int len = etAllocationIn.getText().toString().length();
                        //获取新货位
                        String allocationCodeNew = etAllocationIn.getText().toString().substring(0, len - allocationInLen);
                        //获取旧货位
                        String allocationCodeOld = etAllocationIn.getText().toString().substring(len - allocationInLen, len);
                        if(!allocationCodeNew.equals("")) {
                            //校验货位是否存在
                            int index = BaseData.containsAllocation(allocationCodeNew);
                            if (index >= 0) {
                                //货位存在，更新仓库信息和货位信息
                                if (allocationIn == null) {
                                    allocationIn = BaseData.getAllocations().get(index);
                                    etAllocationIn.setText(allocationCodeNew);
                                    allocationInLen = allocationCodeNew.length();
                                } else if (allocationIn.getIntermediateWarehouseId() == BaseData.getAllocations().get(index).getIntermediateWarehouseId()) {
                                    etAllocationIn.setText(allocationCodeNew);
                                    allocationInLen = allocationCodeNew.length();
                                } else {
                                    //货位非本仓库，提示对应信息
                                    Toast.makeText(ProductMoveActivity.this, "非本仓库货位，请扫描正确货位条码", Toast.LENGTH_LONG).show();
                                    etAllocationIn.setText(allocationCodeOld);
                                }
                            } else {
                                //货位不存在，提示对应信息
                                Toast.makeText(ProductMoveActivity.this, "货位不存在，请扫描正确货位条码", Toast.LENGTH_LONG).show();
                                etAllocationIn.setText(allocationCodeOld);
                            }
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
                                if (product_barcode_list.findBarcode(barcode) >= 0) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductMoveActivity.this);
                                    builder.setTitle("请选择").setMessage("条码" + barcode + "已扫描，要删除吗？");
                                    builder.setPositiveButton("是",new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            product_barcode_list.removeBarcode(barcode);
                                            adapter_product = new ProductAdapter(ProductMoveActivity.this, R.layout.item_product, product_barcode_list.getItems());
                                            lvProduct.setAdapter(adapter_product);
                                            Toast.makeText(ProductMoveActivity.this, "条码" + barcode + "已删除",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    builder.setNeutralButton("否",null);
                                    builder.show();
                                    return true;
                                }
                            }
                            //填充数据
                            CheckMoveBarcodeIn in = new CheckMoveBarcodeIn();
                            in.setUserId(LoginUserInfo.getUserId());
                            in.setBarcode(barcode);
                            in.setDeviceCode(SystemInfo.getDeviceCode());
                            CheckMoveBarcodeOut ws_out = XFrameworkWebServiceUtil.API_CheckMoveBarcode(in  );
                            if (ws_out.getStatus() == 0) {
                                for (PWProductBarcode product_barcode : ws_out.getBarcodes()) {
                                    if (product_barcode_list.findBarcode(product_barcode.getBarcode()) < 0) {
                                        product_barcode_list.addBarocde(product_barcode);
                                    }
                                }
                                adapter_product.notifyDataSetChanged();
                                Toast.makeText(ProductMoveActivity.this, "条码" + etBarcode.getText() + "扫码成功", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ProductMoveActivity.this, "条码" + etBarcode.getText() + "扫码失败：" + ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
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

        //设置产品清单点击事件
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProductMoveActivity.this, BarcodeListActivity.class);
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
        if(allocationIn == null)
        {
            Toast.makeText(this, "货位为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
            return;
        }
        if (product_barcode_list.getBarcodes().size() == 0) {
            Toast.makeText(this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
            return;
        }
        SaveMoveOrderIn ws_in = new SaveMoveOrderIn();

        //设置基础数据
        ws_in.setUserId(LoginUserInfo.getUserId());
        ws_in.setDeviceCode(SystemInfo.getDeviceCode());
        ws_in.setOptionType(0);

        //设置表头
        ws_in.getHead().setIntermediateWarehouseId(allocationIn.getIntermediateWarehouseId());
        ws_in.getHead().setOrderCode(tvOrderCode.getText().toString());
        ws_in.getHead().setInAllocationId(allocationIn.getAllocationId());
        ws_in.getHead().setCreateDate(new Date());
        ws_in.getHead().setCreateUser(LoginUserInfo.getUserName());
        ws_in.getHead().setLastUpdateDate(new Date());
        ws_in.getHead().setLastUpdateUser(LoginUserInfo.getUserName());

        //设置表体
        for (PWProductBarcode barcode : product_barcode_list.getBarcodes()) {
            PWProductMoveOrderDetail detail = new PWProductMoveOrderDetail();
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
        SaveMoveOrderOut ws_out = XFrameworkWebServiceUtil.API_SaveMoveOrder(ws_in);
        Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
        //关闭本页
        if (ws_out.getStatus() == 0) {
            this.finish();
        }
    }
}
