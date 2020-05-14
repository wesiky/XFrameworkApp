package com.xframework.xframeworkapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xframework.adapter.BatchPendListAdapter;
import com.xframework.delegate.BatchDeliveryDelegate;
import com.xframework.item.BaseSection;
import com.xframework.item.BatchItem;
import com.xframework.item.ProductItem;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.MrpSapOrder;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.DeliveryBatchAttIn;
import com.xframework.model.WS.DeliveryBatchAttOut;
import com.xframework.model.WS.GetSuspendedBatchAttListIn;
import com.xframework.model.WS.GetSuspendedBatchAttListOut;
import com.xframework.util.XFrameworkWebServiceUtil;
import java.util.ArrayList;
import java.util.List;

public class BatchPendAttListActivity extends AppCompatActivity implements BatchDeliveryDelegate {
    private int deliveryStartIndex = 0;

    private List<BaseSection> mList = new ArrayList<BaseSection>();

    private ArrayList<MrpSapOrder> order_list = new ArrayList<MrpSapOrder>();

    private BatchPendListAdapter sectionAdapter;

    private void initData() {
        GetSuspendedBatchAttListIn getSuspendedBatchAttListIn = new GetSuspendedBatchAttListIn();
        getSuspendedBatchAttListIn.setUserId(LoginUserInfo.getUserId());
        getSuspendedBatchAttListIn.setDeviceCode(SystemInfo.getDeviceCode());
        GetSuspendedBatchAttListOut getSuspendedBatchAttListOut = XFrameworkWebServiceUtil.API_GetSuspendedBatchAttList(getSuspendedBatchAttListIn);
        if (getSuspendedBatchAttListOut.getStatus() == 0) {
            this.order_list = getSuspendedBatchAttListOut.getOrderList();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getSuspendedBatchAttListOut.getStatus());
            stringBuilder.append(":");
            stringBuilder.append(getSuspendedBatchAttListOut.getMsg());
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            finish();
        }
        String str = "";
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
            productItem.setRemark(mrpSapOrder.getRemark());
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
            } while (this.mList.size() > 0 && !((BaseSection)this.mList.get(this.deliveryStartIndex)).isFirst());
            this.sectionAdapter.notifyDataSetChanged();
        }
    }

    public void SuspendedBatch(String paramString, int paramInt) {}

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        try {
            setContentView(R.layout.activity_batch_pend_att_list);
            initData();
            RecyclerView recyclerView = findViewById(R.id.rvBatchList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            this.sectionAdapter = new BatchPendListAdapter((Context)this, this.mList, this);
            recyclerView.setAdapter(this.sectionAdapter);
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
}


/* Location:              C:\test\dex2jar-2.0\classes2-dex2jar.jar!\com\xframework\xframeworkapp\BatchPendAttListActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */