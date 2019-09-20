package com.xframework.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xframework.delegate.BaseDelegate;
import com.xframework.model.PWCheckOrderHeader;
import com.xframework.model.PWProductBarcode;
import com.xframework.xframeworkapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CheckOrderAdapter extends ArrayAdapter<PWCheckOrderHeader> {
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<PWCheckOrderHeader> items = new ArrayList<PWCheckOrderHeader>();

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public CheckOrderAdapter(Context context, int resource, ArrayList<PWCheckOrderHeader> items) {
        super(context, resource, items);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.items = items;
    }

    public void setGridData(ArrayList<PWCheckOrderHeader> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CheckOrderAdapter.ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new CheckOrderAdapter.ViewHolder();
            holder.tvOrderCodeValue = convertView.findViewById(R.id.tvOrderCodeValue);
            holder.tvWarehouseValue = convertView.findViewById(R.id.tvWarehouseValue);
            holder.tvCheckDateValue = convertView.findViewById(R.id.tvCheckDateValue);
            convertView.setTag(holder);
        } else {
            holder = (CheckOrderAdapter.ViewHolder) convertView.getTag();
        }
        PWCheckOrderHeader item = items.get(position);
        holder.tvOrderCodeValue.setText(item.getOrderCode());
        holder.tvWarehouseValue.setText(item.getIntermediateWarehouseName());
        holder.tvCheckDateValue.setText(format.format(item.getCheckDate()));
        return convertView;
    }

    private class ViewHolder {
        TextView tvOrderCodeValue;
        TextView tvWarehouseValue;
        TextView tvCheckDateValue;
    }
}
