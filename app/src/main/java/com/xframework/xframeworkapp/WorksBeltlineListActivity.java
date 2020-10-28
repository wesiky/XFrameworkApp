package com.xframework.xframeworkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xframework.adapter.LeftAdapter;
import com.xframework.adapter.RightAdapter;
import com.xframework.delegate.WorksBeltlineDelegate;
import com.xframework.model.BaseBeltline;
import com.xframework.model.BaseWorks;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.BaseWSIn;
import com.xframework.model.WS.GetBeltlineListIn;
import com.xframework.model.WS.GetBeltlineListOut;
import com.xframework.model.WS.GetWorksListOut;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.util.ArrayList;

public class WorksBeltlineListActivity extends AppCompatActivity implements WorksBeltlineDelegate {


    RightAdapter rightAdapter;

    private ArrayList<BaseWorks> works_list = new ArrayList<>();
    private ArrayList<BaseBeltline> beltline_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works_beltline_list);
        initView();
    }

    private void initView() {
        RecyclerView rvLeft = findViewById(R.id.rv_left);
        RecyclerView rvRight = findViewById(R.id.rv_right);
        rightAdapter = new RightAdapter(this, R.layout.item_beltline, this);

        //获取盘点清单
        BaseWSIn in = new BaseWSIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetWorksListOut ws_out = XFrameworkWebServiceUtil.API_GetWorksList(in);
        if (ws_out.getStatus() == 0) {
            works_list = ws_out.getWorksList();
        } else {
            //接口调用失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
            this.finish();
        }

        // 左列表
        rvLeft.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) rvLeft.getItemAnimator()).setSupportsChangeAnimations(false);
        LeftAdapter leftAdapter = new LeftAdapter(this, R.layout.item_works, works_list, this);
        rvLeft.setAdapter(leftAdapter);

        // 右列表
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        rvRight.setLayoutManager(gridLayoutManager);
        rvRight.setAdapter(rightAdapter);
//
//        //右侧列表的滚动事件
//        rvRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                //获取右侧列表的第一个可见Item的position
//                int topPosition = ((GridLayoutManager) rightRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                // 如果此项对应的是左边的大类的index
//                if (mRightList.get(topPosition).position != -1) {
//                    MyUtils.moveToMiddle(leftRecyclerView, mRightList.get(topPosition).position);
//                    leftAdapter.setSelectedPosition(mRightList.get(topPosition).position);
//                }
//
//            }
//        });
    }

    public void returnOnClick(View v) {
        this.finish();
    }

    @Override
    public void WorksClick(int works_id) {
        //获取盘点清单
        GetBeltlineListIn in = new GetBeltlineListIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        in.setWorksId(works_id);
        GetBeltlineListOut ws_out = XFrameworkWebServiceUtil.API_GetBeltlineList(in);
        if (ws_out.getStatus() == 0) {
            beltline_list = ws_out.getBeltlineList();
        } else {
            //接口调用失败：提示错误信息
            Toast.makeText(this, ws_out.getStatus() + ":" + ws_out.getMsg(), Toast.LENGTH_LONG).show();
        }

        rightAdapter.setItems(beltline_list);
        rightAdapter.notifyDataSetChanged();
    }

    @Override
    public void BeltlineClick(int beltline_id, String beltline_name) {
        Intent intent = new Intent(this, ComponentDeliveryActivity.class);
        intent.putExtra("beltline_id", beltline_id);
        intent.putExtra("beltline_name", beltline_name);
        startActivityForResult(intent, 0);
    }
}