package com.xframework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.delegate.BatchDeliveryBarcodeDelegate;
import com.xframework.item.BatchProductItem;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;
import java.util.List;

public class BatchProductAdapter  extends RecyclerView.Adapter {
    private Context mContext;
    private int layoutResourceId;
    private List<BatchProductItem> items = new ArrayList<BatchProductItem>();
    BatchDeliveryBarcodeDelegate delegate;

    public BatchProductAdapter(Context mContext, int layoutResourceId, List<BatchProductItem> items, BatchDeliveryBarcodeDelegate delegate) {
        this.mContext = mContext;
        this.items = items;
        this.layoutResourceId = layoutResourceId;
        this.delegate = delegate;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final BatchProductAdapter.ViewHolder holder = new BatchProductAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(layoutResourceId, parent, false));
        holder.view.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                delegate.editBarcode(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BatchProductAdapter.ViewHolder myViewHolder = (BatchProductAdapter.ViewHolder) holder;
        BatchProductItem item = items.get(position);

        myViewHolder.tvProductName.setText(item.getProductName());
        myViewHolder.tvProductBodyName.setText(item.getBodyProductName());
        myViewHolder.tvCountValue.setText(item.getQuantity() + "/" + item.getBatchQuantity());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvProductName;
        TextView tvProductBodyName;
        TextView tvCountValue;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductBodyName = itemView.findViewById(R.id.tvProductBodyName);
            tvCountValue = itemView.findViewById(R.id.tvCountValue);
        }
    }
}
