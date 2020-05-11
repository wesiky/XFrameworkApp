package com.xframework.xframeworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xframework.adapter.BarcodeAdapter;
import com.xframework.delegate.BaseDelegate;
import com.xframework.item.ProductItem;
import com.xframework.model.PWProductBarcode;
import com.xframework.model.ProductBarcodeList;

import java.util.ArrayList;

public class BarcodeListActivity extends AppCompatActivity {
    BarcodeDelegate delegate = new BarcodeDelegate();
    ProductBarcodeList product_barcode_list;
    Intent intent;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barcode_list);
        intent = getIntent();
        int item_index = intent.getIntExtra("item_index", -1);
        product_barcode_list = (ProductBarcodeList) intent.getSerializableExtra("product_barcode_list");
        if (item_index >= 0) {
            TextView tvProductCodeValue = findViewById(R.id.tvProductCodeValue);
            TextView tvProductNameValue = findViewById(R.id.tvProductNameValue);
            ListView lvBarcode = findViewById(R.id.lvBarcode);

            TextView emptyView = new TextView(this);
            emptyView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));
            emptyView.setText("无条码数据");
            emptyView.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
            emptyView.setVisibility(View.GONE);
            ((ViewGroup)lvBarcode.getParent()).addView(emptyView);
            lvBarcode.setEmptyView(emptyView);
            //设置产品信息
            ProductItem product_item = product_barcode_list.getItems().get(item_index);
            tvProductCodeValue.setText(product_item.getProductCode());
            tvProductNameValue.setText(product_item.getProductName());
            //设置条码清单
            ArrayList<PWProductBarcode> barcodes = new ArrayList<>();
            for(PWProductBarcode barcode : product_barcode_list.getBarcodes()){
                if(barcode.getProductId() == product_item.getProductId()){
                    barcodes.add(barcode);
                }
            }
            BarcodeAdapter adapter_barcode = new BarcodeAdapter(this, R.layout.item_barcode, barcodes,delegate);
            lvBarcode.setAdapter(adapter_barcode);
        } else {
            this.finish();
        }
    }

    public void returnOnClick(View v) {
        this.finish();
    }

    public void saveOnClick(View v) {
        intent.putExtra("product_barcode_list",product_barcode_list);
        setResult(RESULT_OK,intent);
        this.finish();
    }

    private class BarcodeDelegate implements BaseDelegate{
        @Override
        public boolean removeBarcodes(String barcode) {
            if(product_barcode_list != null) {
                product_barcode_list.removeBarcode(barcode);
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
