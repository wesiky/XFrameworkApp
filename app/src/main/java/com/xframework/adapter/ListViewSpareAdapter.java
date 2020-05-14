package com.xframework.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.xframework.model.SpareItem;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;

public class ListViewSpareAdapter extends ArrayAdapter<SpareItem> {
    private ArrayList<SpareItem> items;

    private int layoutResourceId;

    private Context mContext;

    private int type;

    public ListViewSpareAdapter(Context mContext, int layoutResourceId, ArrayList<SpareItem> items, int type) {
        super(mContext, layoutResourceId, items);
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
        this.type = type;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvSpare = convertView.findViewById(R.id.tvSpare);
            viewHolder.tvCount = convertView.findViewById(R.id.tvCount);
            viewHolder.tvCode = convertView.findViewById(R.id.tvCode);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        SpareItem spareItem = this.items.get(position);
        viewHolder.tvSpare.setText(spareItem.getSpareName() + "|" + spareItem.getSpareModel());
        viewHolder.tvCode.setText(spareItem.getSpareCode());
        if (type == 0) {
            viewHolder.tvCount.setText(String.valueOf(spareItem.getQuantity()));
        }
        else {
            viewHolder.tvCount.setText(spareItem.getQuantity() + "/" + spareItem.getSumQuantity());
        }
        return convertView;
    }

    public void setItems(ArrayList<SpareItem> items) {
        this.items = items;
    }

    private class ViewHolder {
        TextView tvCode;

        TextView tvCount;

        TextView tvSpare;

        private ViewHolder() {}
    }
}
