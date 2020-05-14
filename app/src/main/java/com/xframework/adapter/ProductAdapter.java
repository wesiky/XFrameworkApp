package com.xframework.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xframework.item.ProductItem;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<ProductItem> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<ProductItem> items;

    public ProductAdapter(Context context, int resource, ArrayList<ProductItem> items) {
        super(context, resource, items);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.items = items;
    }

    public void setItems(ArrayList<ProductItem> items) {
        this.items = items;
    }

    public void setGridData(ArrayList<ProductItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ProductAdapter.ViewHolder();
            holder.tvProductName = convertView.findViewById(R.id.tvProductName);
            holder.tvProductCode = convertView.findViewById(R.id.tvProductCode);
            holder.tvCountValue = convertView.findViewById(R.id.tvCountValue);
            convertView.setTag(holder);
        } else {
            holder = (ProductAdapter.ViewHolder) convertView.getTag();
        }
        ProductItem item = items.get(position);
        holder.tvProductName.setText(item.getProductName());
        holder.tvProductCode.setText(item.getProductCode());
        holder.tvCountValue.setText(String.valueOf(item.getQuantity()));
        return convertView;
    }

    private class ViewHolder {
        TextView tvProductName;
        TextView tvProductCode;
        TextView tvCountValue;
    }
}
