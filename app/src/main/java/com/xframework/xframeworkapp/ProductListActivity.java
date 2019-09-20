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
import com.xframework.model.LoginUserInfo;
import com.xframework.model.PWCheckOrderDetailAllocation;
import com.xframework.model.PWProductBarcode;
import com.xframework.model.ProductBarcodeList;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.CheckAllocationCheckBarcodeIn;
import com.xframework.model.WS.CheckAllocationCheckBarcodeOut;
import com.xframework.model.WS.SaveAllocationCheckDetailIn;
import com.xframework.model.WS.SaveAllocationCheckDetailOut;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.util.Date;

public class ProductListActivity extends AppCompatActivity {

    TextView tvWarehouse;
    TextView tvAllocation;
    EditText etBarcode;
    ListView lvProduct;

    private BaseAllocation allocation;
    private String orderCode;
    ProductAdapter adapter_product;
    ProductBarcodeList product_barcode_list = new ProductBarcodeList();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product_list);

        intent = getIntent();

        tvWarehouse = findViewById(R.id.tvWarehouseValue);
        tvAllocation = findViewById(R.id.tvAllocationTitle);
        etBarcode = findViewById(R.id.etBarcode);
        lvProduct = findViewById(R.id.lvProduct);

        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));
        emptyView.setText("未扫描产品数据");
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)lvProduct.getParent()).addView(emptyView);
        lvProduct.setEmptyView(emptyView);

        //禁止键盘弹出
        etBarcode.setInputType(InputType.TYPE_NULL);

        allocation = (BaseAllocation)getIntent().getSerializableExtra("allocation");
        orderCode = getIntent().getStringExtra("order_code");
        tvWarehouse.setText(allocation.getIntermediateWarehouseName());
        tvAllocation.setText(allocation.getAllocationCode());

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
                                AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this);
                                builder.setTitle("请选择").setMessage("条码" + barcode + "已扫描，要删除吗？");
                                builder.setPositiveButton("是",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        product_barcode_list.removeBarcode(barcode);
                                        adapter_product = new ProductAdapter(ProductListActivity.this, R.layout.item_product, product_barcode_list.getItems());
                                        lvProduct.setAdapter(adapter_product);
                                        Toast.makeText(ProductListActivity.this, "条码" + barcode + "已删除",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNeutralButton("否",null);
                                builder.show();
                                return true;
                            }
                        }
                        //填充产品数据
                        CheckAllocationCheckBarcodeIn in = new CheckAllocationCheckBarcodeIn();
                        in.setUserId(LoginUserInfo.getUserId());
                        in.setWarehouseId(allocation.getIntermediateWarehouseId());
                        in.setBarcode(barcode);
                        in.setDeviceCode(SystemInfo.getDeviceCode());
                        CheckAllocationCheckBarcodeOut ws_out = XFrameworkWebServiceUtil.API_CheckAllocationCheckBarcode(in);
                        if (ws_out.getStatus() == 0) {
                            for (PWProductBarcode product_barcode : ws_out.getBarcodes()) {
                                if (product_barcode_list.findBarcode(product_barcode.getBarcode()) < 0) {
                                    product_barcode_list.addBarocde(product_barcode);
                                }
                            }
                            adapter_product.notifyDataSetChanged();
                            Toast.makeText(ProductListActivity.this, "条码" + etBarcode.getText() + "扫码成功", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ProductListActivity.this, "条码" + etBarcode.getText() + "扫码失败：" + ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(ProductListActivity.this, BarcodeListActivity.class);
                intent.putExtra("item_index",i);
                intent.putExtra("product_barcode_list",product_barcode_list);
                startActivityForResult(intent,0);
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
        if (product_barcode_list.getBarcodes().size() == 0) {
            Toast.makeText(this, "条码为空，请扫码后再保存！", Toast.LENGTH_LONG).show();
            return;
        }
        SaveAllocationCheckDetailIn ws_in = new SaveAllocationCheckDetailIn();

        //设置基础数据
        ws_in.setUserId(LoginUserInfo.getUserId());
        ws_in.setDeviceCode(SystemInfo.getDeviceCode());
        ws_in.setOptionType(0);
        ws_in.setOrderCode(orderCode);
        ws_in.setAllocationId(allocation.getAllocationId());

        //设置表体
        for (PWProductBarcode barcode : product_barcode_list.getBarcodes()) {
            PWCheckOrderDetailAllocation detail = new PWCheckOrderDetailAllocation();
            detail.setCheckAllocationId(allocation.getAllocationId());
            detail.setOrderCode(orderCode);
            detail.setOuterBarcode(barcode.getOuterBarcode());
            detail.setProductBarcode(barcode.getBarcode());
            detail.setProductId(barcode.getProductId());
            detail.setCreateDate(new Date());
            detail.setCreateUser(LoginUserInfo.getUserName());
            detail.setLastUpdateDate(new Date());
            detail.setLastUpdateUser(LoginUserInfo.getUserName());
            detail.setEnable(true);
            ws_in.getBody().add(detail);
        }

        //保存单据
        SaveAllocationCheckDetailOut ws_out = XFrameworkWebServiceUtil.API_SaveAllocationCheckDetail(ws_in);

        Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
        //关闭本页
        if (ws_out.getStatus() == 0) {
            setResult(RESULT_CANCELED,intent);
            this.finish();
        }
    }
}
