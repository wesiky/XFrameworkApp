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

import com.xframework.adapter.CheckOrderAdapter;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.PWCheckOrderHeader;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.GetCheckOrderListIn;
import com.xframework.model.WS.GetCheckOrderListOut;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.util.ArrayList;

public class CheckListActivity extends AppCompatActivity {

    private ListView lvOrder;
    private CheckOrderAdapter adapter_check;
    private ArrayList<PWCheckOrderHeader> order_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_check_list);

        lvOrder = findViewById(R.id.lvOrder);
        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));
        emptyView.setText("无未处理盘库单");
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)lvOrder.getParent()).addView(emptyView);
        lvOrder.setEmptyView(emptyView);

        //获取盘点清单
        GetCheckOrderListIn in = new GetCheckOrderListIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetCheckOrderListOut ws_out = XFrameworkWebServiceUtil.API_GetCheckOrderList(in);
        if (ws_out.getStatus() == 0) {
            order_list = ws_out.getOrderList();
        } else {
            //接口调用失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
        }

        //设置盘点清单点击事件
        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CheckListActivity.this, CheckActivity.class);
                intent.putExtra("order_code",order_list.get(i));
                startActivity(intent);
            }
        });

        //设置产品清单
        adapter_check = new CheckOrderAdapter(this, R.layout.item_check_order, order_list);
        lvOrder.setAdapter(adapter_check);
    }

    public void returnOnClick(View v) {
        this.finish();
    }
}
