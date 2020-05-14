package com.xframework.xframeworkapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xframework.adapter.SectionAdapter;
import com.xframework.delegate.BatchDeliveryDelegate;
import com.xframework.item.BaseSection;
import com.xframework.item.BatchItem;
import com.xframework.item.ProductItem;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.MrpSapOrder;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.DeliveryBatchAttIn;
import com.xframework.model.WS.DeliveryBatchAttOut;
import com.xframework.model.WS.GetDeliveryBatchAttListIn;
import com.xframework.model.WS.GetDeliveryBatchAttListOut;
import com.xframework.util.XFrameworkWebServiceUtil;
import java.util.ArrayList;
import java.util.List;

public class BatchAttDeliveryListActivity extends AppCompatActivity implements BatchDeliveryDelegate {
    private int deliveryStartIndex = 0;

    private List<BaseSection> mList = new ArrayList<BaseSection>();

    private ArrayList<MrpSapOrder> order_list = new ArrayList<MrpSapOrder>();

    RecyclerView rvBatchList;

    private SectionAdapter sectionAdapter;

    private void initData() {
        String str = "";
        GetDeliveryBatchAttListIn getDeliveryBatchAttListIn = new GetDeliveryBatchAttListIn();
        getDeliveryBatchAttListIn.setUserId(LoginUserInfo.getUserId());
        getDeliveryBatchAttListIn.setDeviceCode(SystemInfo.getDeviceCode());
        GetDeliveryBatchAttListOut getDeliveryBatchAttListOut = XFrameworkWebServiceUtil.API_GetDeliveryBatchAttList(getDeliveryBatchAttListIn);
        if (getDeliveryBatchAttListOut.getStatus() == 0) {
            this.order_list = getDeliveryBatchAttListOut.getOrderList();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getDeliveryBatchAttListOut.getStatus());
            stringBuilder.append(":");
            stringBuilder.append(getDeliveryBatchAttListOut.getMsg());
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            finish();
        }
        for (MrpSapOrder mrpSapOrder : this.order_list) {
            String str1 = str;
            if (!str.equals(mrpSapOrder.getBatchNo())) {
                str1 = mrpSapOrder.getBatchNo();
                BatchItem batchItem = new BatchItem();
                batchItem.setBatchNo(str1);
                this.mList.add(new BaseSection(true, batchItem));
            }
            ProductItem productItem = new ProductItem();
            productItem.setProductCode(mrpSapOrder.getProductCode());
            productItem.setProductName(mrpSapOrder.getProductName());
            productItem.setQuantity(mrpSapOrder.getCount());
            productItem.setStatus(mrpSapOrder.getStatus());
            this.mList.add(new BaseSection(false, productItem));
            String str2 = str1;
        }
    }

    public void DeliveryBatch(String paramString, int paramInt) {
        this.deliveryStartIndex = paramInt;
        DeliveryBatchAttIn deliveryBatchAttIn = new DeliveryBatchAttIn();
        deliveryBatchAttIn.setBatchNo(paramString);
        deliveryBatchAttIn.setUserId(LoginUserInfo.getUserId());
        deliveryBatchAttIn.setDeviceCode(SystemInfo.getDeviceCode());
        DeliveryBatchAttOut deliveryBatchAttOut = XFrameworkWebServiceUtil.API_DeliveryBatchAtt(deliveryBatchAttIn);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(deliveryBatchAttOut.getStatus());
        stringBuilder.append(":");
        stringBuilder.append(deliveryBatchAttOut.getMsg());
        Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
        if (deliveryBatchAttOut.getStatus() == 0) {
            do {
                this.mList.remove(this.deliveryStartIndex);
            } while (this.mList.size() > 0 && !this.mList.get(this.deliveryStartIndex).isFirst());
            this.sectionAdapter.notifyDataSetChanged();
        }
    }

    public void SuspendedBatch(String paramString, int paramInt) {
        this.deliveryStartIndex = paramInt;
        Intent intent = new Intent((Context)this, BatchPendActivity.class);
        intent.putExtra("batch_no", paramString);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt2 == -1)
            try {
                do {
                    this.mList.remove(this.deliveryStartIndex);
                } while (this.mList.size() > 0 && !((BaseSection)this.mList.get(this.deliveryStartIndex)).isFirst());
                this.sectionAdapter = new SectionAdapter((Context)this, this.mList, this);
                this.rvBatchList.setAdapter((RecyclerView.Adapter)this.sectionAdapter);
            } catch (Exception exception) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getClass().getName());
                stringBuilder.append(" onActivityResult: ");
                stringBuilder.append(exception.getMessage());
                Log.e("SystemError", stringBuilder.toString());
                stringBuilder = new StringBuilder();
                stringBuilder.append("程序异常，请联系管理员，异常原因：");
                stringBuilder.append(exception.getMessage());
                Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                finish();
                return;
            }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        try {
            setContentView(R.layout.activity_batch_att_delivery_list);
            initData();
            this.rvBatchList = findViewById(R.id.rvBatchList);
            this.rvBatchList.setLayoutManager(new LinearLayoutManager(this));
            this.sectionAdapter = new SectionAdapter(this, mList, this);
            this.rvBatchList.setAdapter(sectionAdapter);
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
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    public void returnOnClick(View paramView) {
        finish();
    }
}