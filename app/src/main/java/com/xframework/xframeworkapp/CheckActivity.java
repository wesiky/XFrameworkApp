package com.xframework.xframeworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xframework.adapter.AllocationAdapter;
import com.xframework.delegate.AllocationDelegate;
import com.xframework.model.BaseAllocation;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.PWCheckOrderHeader;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.DeleteAllocationCheckDetailIn;
import com.xframework.model.WS.DeleteAllocationCheckDetailOut;
import com.xframework.model.WS.GetCheckAllocationListIn;
import com.xframework.model.WS.GetCheckAllocationListOut;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity implements AllocationDelegate {

    TextView tvOrderCode;
    TextView tvWarehouse;
    TextView tvCheckDate;
    ListView lvAllocation;

    private AllocationAdapter adapter_allocation;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private ArrayList<BaseAllocation> allocations = new ArrayList<>();

    private PWCheckOrderHeader orderHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_check);

        tvOrderCode = findViewById(R.id.tvOrderCodeValue);
        tvWarehouse = findViewById(R.id.tvWarehouseValue);
        tvCheckDate = findViewById(R.id.tvCheckDateValue);
        lvAllocation = findViewById(R.id.lvAllocation);

        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));
        emptyView.setText("仓库未设置货位");
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)lvAllocation.getParent()).addView(emptyView);
        lvAllocation.setEmptyView(emptyView);

        Intent intent = getIntent();
        orderHeader = (PWCheckOrderHeader)intent.getSerializableExtra("order_code");
        tvOrderCode.setText(orderHeader.getOrderCode());
        tvWarehouse.setText(orderHeader.getIntermediateWarehouseName());
        tvCheckDate.setText(format.format(orderHeader.getCheckDate()));

        //获取盘点清单
        GetCheckAllocationListIn in = new GetCheckAllocationListIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        in.setOrderCode(orderHeader.getOrderCode());
        GetCheckAllocationListOut ws_out = XFrameworkWebServiceUtil.API_GetCheckAllocationList(in);
        if (ws_out.getStatus() == 0) {
            allocations = ws_out.getAllocations();
        } else {
            //接口调用失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
        }

        //设置盘点清单点击事件
        lvAllocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BaseAllocation allocation = allocations.get(i);
                Intent intent = new Intent(CheckActivity.this, ProductListActivity.class);
                intent.putExtra("order_code",orderHeader.getOrderCode());
                intent.putExtra("allocation",allocation);
                startActivity(intent);
            }
        });

        //设置产品清单
        adapter_allocation = new AllocationAdapter(this, R.layout.item_check_allocation, allocations,this,orderHeader.getOrderCode());
        lvAllocation.setAdapter(adapter_allocation);
    }

    public void returnOnClick(View v) {
        this.finish();
    }

    @Override
    public boolean recheck(String orderCode, BaseAllocation allocation) {
        //调用webservice删除记录
        //获取盘点清单
        DeleteAllocationCheckDetailIn in = new DeleteAllocationCheckDetailIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        in.setOrderCode(orderHeader.getOrderCode());
        in.setAllocationId(allocation.getAllocationId());
        DeleteAllocationCheckDetailOut ws_out = XFrameworkWebServiceUtil.API_DeleteAllocationCheckDetail(in);
        if (ws_out.getStatus() == 0) {
            //接口调用失败：提示信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            allocation.setStatus(0);
            adapter_allocation.notifyDataSetChanged();
            return true;
        } else {
            //接口调用失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
            return false;
        }
    }

    //结果处理函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取盘点清单
        GetCheckAllocationListIn in = new GetCheckAllocationListIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        in.setOrderCode(orderHeader.getOrderCode());
        GetCheckAllocationListOut ws_out = XFrameworkWebServiceUtil.API_GetCheckAllocationList(in);
        if (ws_out.getStatus() == 0) {
            allocations = ws_out.getAllocations();
        } else {
            //接口调用失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
        }

        //设置产品清单
        adapter_allocation.setGridData(allocations);
        adapter_allocation.notifyDataSetChanged();
    }
}
