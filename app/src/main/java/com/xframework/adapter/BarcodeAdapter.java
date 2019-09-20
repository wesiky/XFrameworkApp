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
import com.xframework.model.PWProductBarcode;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;

public class BarcodeAdapter extends ArrayAdapter<PWProductBarcode> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<PWProductBarcode> items = new ArrayList<PWProductBarcode>();
    private BaseDelegate delegate;

    public BarcodeAdapter(Context context, int resource, ArrayList<PWProductBarcode> items,BaseDelegate delegate) {
        super(context, resource, items);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.items = items;
        this.delegate = delegate;
    }

    public void setGridData(ArrayList<PWProductBarcode> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BarcodeAdapter.ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, null);
            holder = new BarcodeAdapter.ViewHolder();
            holder.tvBarcodeValue = convertView.findViewById(R.id.tvBarcodeValue);
            holder.tvOuterBarcodeValue = convertView.findViewById(R.id.tvOuterBarcodeValue);
            holder.btnDelete = convertView.findViewById(R.id.btnRecheck);
            convertView.setTag(holder);
        } else {
            holder = (BarcodeAdapter.ViewHolder) convertView.getTag();
        }
        PWProductBarcode item = items.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delegate.removeBarcodes(items.get(position).getBarcode())) {
                    items.remove(position);
                    notifyDataSetChanged();
                }
                else{
                    Toast.makeText(mContext,"条码删除失败，请联系管理员",Toast.LENGTH_LONG);
                }
            }
        });
        holder.tvBarcodeValue.setText(item.getBarcode());
        holder.tvOuterBarcodeValue.setText(item.getOuterBarcode());
        return convertView;
    }

    private class ViewHolder {
        TextView tvBarcodeValue;
        TextView tvOuterBarcodeValue;
        ImageButton btnDelete;
    }
}
