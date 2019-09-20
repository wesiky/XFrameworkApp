package com.xframework.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xframework.delegate.AllocationDelegate;
import com.xframework.model.BaseAllocation;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;

public class AllocationAdapter  extends ArrayAdapter<BaseAllocation> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<BaseAllocation> items = new ArrayList<BaseAllocation>();
    private AllocationDelegate delegate;
    private String orderCode;

    public AllocationAdapter(Context context, int resource, ArrayList<BaseAllocation> items,AllocationDelegate delegate,String orderCode) {
        super(context, resource, items);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.items = items;
        this.delegate = delegate;
        this.orderCode = orderCode;
    }

    public void setGridData(ArrayList<BaseAllocation> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AllocationAdapter.ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, null);
            holder = new AllocationAdapter.ViewHolder();
            holder.tvStatus = convertView.findViewById(R.id.tvProductNameTitle);
            holder.tvAllocation = convertView.findViewById(R.id.tvAllocationTitle);
            holder.btnRecheck = convertView.findViewById(R.id.btnRecheck);
            convertView.setTag(holder);
        } else {
            holder = (AllocationAdapter.ViewHolder) convertView.getTag();
        }

        final BaseAllocation item = items.get(position);

        holder.btnRecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出提示
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("请选择");
                builder.setMessage("确定重盘吗？");
                builder.setPositiveButton("否",null);
                builder.setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delegate.recheck(orderCode,item);
                    }
                });
                builder.show();
            }
        });

        if(item.getStatus()==0) {
            holder.tvStatus.setText("待盘点");
            holder.btnRecheck.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.tvStatus.setText("已盘点");
            holder.btnRecheck.setVisibility(View.VISIBLE);
        }
        holder.tvAllocation.setText(item.getAllocationCode());
        return convertView;
    }

    private class ViewHolder {
        TextView tvStatus;
        TextView tvAllocation;
        ImageButton btnRecheck;
    }
}
