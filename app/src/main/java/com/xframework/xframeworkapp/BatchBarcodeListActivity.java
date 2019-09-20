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
import com.xframework.item.BatchProductItem;
import com.xframework.item.ProductItem;
import com.xframework.model.BatchProductBarcodeList;
import com.xframework.model.PWProductBarcode;
import com.xframework.model.ProductBarcodeList;

import java.util.ArrayList;

public class BatchBarcodeListActivity extends AppCompatActivity {
    BatchBarcodeListActivity.BarcodeDelegate delegate = new BatchBarcodeListActivity.BarcodeDelegate();
    BatchProductBarcodeList batch_product_barcode_list;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barcode_list);
        intent = getIntent();
        int item_index = intent.getIntExtra("item_index", -1);
        batch_product_barcode_list = (BatchProductBarcodeList) intent.getSerializableExtra("batch_product_barcode_list");
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
            BatchProductItem batch_product_item = batch_product_barcode_list.getItems().get(item_index);
            tvProductCodeValue.setText(batch_product_item.getBodyProductCode());
            tvProductNameValue.setText(batch_product_item.getBodyProductName());
            //设置条码清单
            ArrayList<PWProductBarcode> barcodes = new ArrayList<>();
            for(PWProductBarcode barcode : batch_product_barcode_list.getBarcodes()){
                if(barcode.getProductId() == batch_product_item.getBodyProductId()){
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
        intent.putExtra("batch_product_barcode_list",batch_product_barcode_list);
        setResult(RESULT_OK,intent);
        this.finish();
    }

    private class BarcodeDelegate implements BaseDelegate {
        @Override
        public boolean removeBarcodes(String barcode) {
            if(batch_product_barcode_list != null) {
                batch_product_barcode_list.removeBarcode(barcode);
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
