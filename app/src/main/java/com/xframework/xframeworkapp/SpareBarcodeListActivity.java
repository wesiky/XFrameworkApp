package com.xframework.xframeworkapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.adapter.SpareBarcodeAdapter;
import com.xframework.delegate.BaseDelegate;
import com.xframework.model.SWSpareBarcode;
import com.xframework.model.SpareBarcodeList;
import com.xframework.model.SpareItem;

import java.util.ArrayList;

import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class SpareBarcodeListActivity extends AppCompatActivity {
    BarcodeDelegate delegate = new BarcodeDelegate();

    Intent intent;

    RecyclerView rvBarcode;

    SpareBarcodeList spare_barcode_list;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            requestWindowFeature(FEATURE_NO_TITLE);
            getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
            setContentView(R.layout.activity_spare_barcode_list);
            this.intent = getIntent();
            int index = intent.getIntExtra("item_index", -1);
            this.spare_barcode_list = (SpareBarcodeList)intent.getSerializableExtra("spare_barcode_list");
            if (index >= 0) {
                rvBarcode = findViewById(R.id.lvBarcode);
                rvBarcode.setLayoutManager(new LinearLayoutManager(this));
                SpareItem item = this.spare_barcode_list.getItems().get(index);
                ArrayList<SWSpareBarcode> spareBarcodes = new ArrayList<>();
                for (SWSpareBarcode barcode : spare_barcode_list.getBarcodes()) {
                    if (barcode.getSpareId() == item.getSpareId())
                        spareBarcodes.add(barcode);
                }
                SpareBarcodeAdapter spareBarcodeAdapter = new SpareBarcodeAdapter(this, R.layout.item_spare_barcode_chang, spareBarcodes, this.delegate);
                this.rvBarcode.setAdapter(spareBarcodeAdapter);
            } else {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void returnOnClick(View v) {
        finish();
    }

    public void saveOnClick(View v) {
        try {
            this.spare_barcode_list.RefreshItemsQuantity();
            this.intent.putExtra("spare_barcode_list", this.spare_barcode_list);
            setResult(RESULT_OK, this.intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "程序异常，请联系管理员，异常原因：" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private class BarcodeDelegate implements BaseDelegate {
        private BarcodeDelegate() {}

        public boolean removeBarcodes(String barcode) {
            if (spare_barcode_list != null) {
                spare_barcode_list.removeBarcode(barcode);
                return true;
            }
            return false;
        }
    }
}