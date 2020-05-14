package com.xframework.xframeworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.adapter.BatchPendAdapter;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.MrpSapOrder;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.GetBatchOrderInfoIn;
import com.xframework.model.WS.GetBatchOrderInfoOut;
import com.xframework.model.WS.SuspendedBatchAttIn;
import com.xframework.model.WS.SuspendedBatchAttOut;
import com.xframework.model.WS.SuspendedBatchOrderIn;
import com.xframework.model.WS.SuspendedBatchOrderOut;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.util.ArrayList;

public class BatchPendActivity extends AppCompatActivity {

    private TextView tvBatchNo;
    private RecyclerView rvProductList;
    private BatchPendAdapter sectionAdapter;
    Intent intent;
    private ArrayList<MrpSapOrder> batchItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_pend);

        tvBatchNo = findViewById(R.id.tvBatchNo);

        intent = getIntent();

        initData();
        rvProductList = findViewById(R.id.rvProductList);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));
        sectionAdapter = new BatchPendAdapter(this,batchItems);
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
            batchItems = ws_out.getOrderList();
        } else {
            //单号获取失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,intent);
            this.finish();
        }
    }


    public void returnOnClick(View v) {
        this.finish();
    }

    public void saveOnClick(View v) {
        //挂起批单
        SuspendedBatchAttIn in = new SuspendedBatchAttIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        in.setBatchNo(tvBatchNo.getText().toString());
        in.setProductList(batchItems);
        SuspendedBatchAttOut ws_out = XFrameworkWebServiceUtil.API_SuspendedBatchAtt(in);
        Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
        if (ws_out.getStatus() == 0) {
            setResult(RESULT_OK);
            this.finish();
        }
    }
}
