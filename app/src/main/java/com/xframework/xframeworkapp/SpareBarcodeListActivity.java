package com.xframework.xframeworkapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xframework.adapter.SpareBarcodeAdapter;
import com.xframework.delegate.BaseDelegate;
import com.xframework.model.SWSpareBarcode;
import com.xframework.model.SpareBarcodeList;
import com.xframework.model.SpareItem;
import java.io.Serializable;
import java.util.ArrayList;

public class SpareBarcodeListActivity extends AppCompatActivity {
    BarcodeDelegate delegate = new BarcodeDelegate();

    Intent intent;

    RecyclerView rvBarcode;

    SpareBarcodeList spare_barcode_list;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        try {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            setContentView(R.layout.activity_spare_barcode_list);
            this.intent = getIntent();
            int i = this.intent.getIntExtra("item_index", -1);
            this.spare_barcode_list = (SpareBarcodeList)this.intent.getSerializableExtra("spare_barcode_list");
            if (i >= 0) {
                this.rvBarcode = findViewById(R.id.tvBarcode);
                this.rvBarcode.setLayoutManager(new LinearLayoutManager((Context)this));
                SpareItem spareItem = this.spare_barcode_list.getItems().get(i);
                ArrayList<SWSpareBarcode> arrayList = new ArrayList();
                for (SWSpareBarcode sWSpareBarcode : this.spare_barcode_list.getBarcodes()) {
                    if (sWSpareBarcode.getSpareId() == spareItem.getSpareId())
                        arrayList.add(sWSpareBarcode);
                }
                SpareBarcodeAdapter spareBarcodeAdapter = new SpareBarcodeAdapter((Context)this, 2131427416, arrayList, this.delegate);
                this.rvBarcode.setAdapter((RecyclerView.Adapter)spareBarcodeAdapter);
            } else {
                finish();
            }
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

    public void returnOnClick(View v) {
        finish();
    }

    public void saveOnClick(View v) {
        try {
            this.spare_barcode_list.RefreshItemsQuantity();
            this.intent.putExtra("spare_barcode_list", this.spare_barcode_list);
            setResult(-1, this.intent);
        } catch (Exception exception) {

        } finally {
            Exception exception;
        }
        finish();
    }

    private class BarcodeDelegate implements BaseDelegate {
        private BarcodeDelegate() {}

        public boolean removeBarcodes(String param1String) {
            if (SpareBarcodeListActivity.this.spare_barcode_list != null) {
                SpareBarcodeListActivity.this.spare_barcode_list.removeBarcode(param1String);
                return true;
            }
            return false;
        }
    }
}