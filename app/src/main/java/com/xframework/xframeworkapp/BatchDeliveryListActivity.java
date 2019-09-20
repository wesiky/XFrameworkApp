package com.xframework.xframeworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xframework.adapter.SectionAdapter;
import com.xframework.delegate.BatchDeliveryDelegate;
import com.xframework.item.BaseSection;
import com.xframework.item.BatchItem;
import com.xframework.item.ProductItem;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.MrpSapOrder;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.GetDeliveryBatchOrderListIn;
import com.xframework.model.WS.GetDeliveryBatchOrderListOut;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.util.ArrayList;
import java.util.List;



public class BatchDeliveryListActivity extends AppCompatActivity implements BatchDeliveryDelegate{


    private List<BaseSection> mList = new ArrayList<>();
    private ArrayList<MrpSapOrder> order_list = new ArrayList<>();
    private SectionAdapter sectionAdapter;
    private int deliveryStartIndex =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_delivery_list);

        initData();
        androidx.recyclerview.widget.RecyclerView rvBatchList = findViewById(R.id.rvBatchList);
        rvBatchList.setLayoutManager(new LinearLayoutManager(this));
        sectionAdapter = new SectionAdapter(this, mList, this);
        rvBatchList.setAdapter(sectionAdapter);
    }

    private void initData() {

        //获取批单
        GetDeliveryBatchOrderListIn in = new GetDeliveryBatchOrderListIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetDeliveryBatchOrderListOut ws_out = XFrameworkWebServiceUtil.API_GetDeliveryBatchOrderList(in);
        if (ws_out.getStatus() == 0) {
            order_list = ws_out.getOrderList();
        } else {
            //接口调用失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
        }
        String batchNo = "";
        for (MrpSapOrder order : order_list) {
            if (!batchNo.equals(order.getBatchNo())) {
                batchNo = order.getBatchNo();
                BatchItem batchItem = new BatchItem();
                batchItem.setBatchNo(batchNo);
                mList.add(new BaseSection(true, batchItem));
            }
            ProductItem productItem = new ProductItem();
            productItem.setProductCode(order.getProductCode());
            productItem.setProductName(order.getProductName());
            productItem.setQuantity(order.getCount());
            productItem.setStatus(order.getStatus());
            mList.add(new BaseSection(false, productItem));
        }
    }

    public void returnOnClick(View v) {
        this.finish();
    }

    //结果处理函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            do {
                sectionAdapter.notifyItemRemoved(deliveryStartIndex);
                mList.remove(deliveryStartIndex);
            } while (mList.size() > 0 && !mList.get(deliveryStartIndex).isFirst());
    }

    @Override
    public void DeliveryBatch(String batchNo,int startIndex) {
        deliveryStartIndex = startIndex;
        Intent intent = new Intent(BatchDeliveryListActivity.this, BatchDeliveryActivity.class);
        intent.putExtra("batch_no", batchNo);
        startActivityForResult(intent, 0);
    }

    @Override
    public void SuspendedBatch(String batchNo, int startIndex) {
        deliveryStartIndex = startIndex;
        Intent intent = new Intent(BatchDeliveryListActivity.this, BatchPendActivity.class);
        intent.putExtra("batch_no", batchNo);
        startActivityForResult(intent, 0);
    }
}
