package com.xframework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.delegate.BatchDeliveryDelegate;
import com.xframework.item.BaseSection;
import com.xframework.item.BatchItem;
import com.xframework.item.ProductItem;
import com.xframework.xframeworkapp.R;

import java.util.List;

public class BatchPendListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<BaseSection> mList;
    private BatchDeliveryDelegate delegate;

    public BatchPendListAdapter(Context mContext, List<BaseSection> mList,BatchDeliveryDelegate delegate) {
        this.mContext = mContext;
        this.mList = mList;
        this.delegate = delegate;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (mList.get(position).isFirst()){
            viewType = 0; //这个是第一条
        }else {
            viewType = 1;
        }
        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:
                final BatchPendListAdapter.ViewHolder holder0 = new BatchPendListAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_batch, parent, false), viewType);
                holder0.tvDelivery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder0.getAdapterPosition();
                        delegate.DeliveryBatch(((BatchItem) mList.get(position).getTag()).getBatchNo(),position);

                    }
                });
                holder0.tvSuspended.setVisibility(View.GONE);
                return holder0;
            case 1:
                return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_batch_pend, parent, false), viewType);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BatchPendListAdapter.ViewHolder myViewHolder = (BatchPendListAdapter.ViewHolder) holder;
        switch(myViewHolder.viewType){
            case 0:
                BatchItem itemBatch = (BatchItem)mList.get(position).getTag();
                myViewHolder.tvBatchValue.setText(itemBatch.getBatchNo());
                break;
            case 1:
                ProductItem itemProduct = (ProductItem)mList.get(position).getTag();
                myViewHolder.tvProductName.setText(itemProduct.getProductName());
                myViewHolder.tvCountValue.setText(String.valueOf(itemProduct.getQuantity()));
                myViewHolder.etRemark.setText(itemProduct.getRemark());
                myViewHolder.etRemark.setEnabled(false);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvBatchValue;
        TextView tvSuspended;
        TextView tvDelivery;

        TextView tvProductName;
        TextView tvCountValue;
        EditText etRemark;
        int viewType;
        private ViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            switch(viewType){
                case 0:
                    tvBatchValue = itemView.findViewById(R.id.tvBatchValue);
                    tvSuspended = itemView.findViewById(R.id.tvLackAttachment);
                    tvDelivery = itemView.findViewById(R.id.tvLackBody);
                    break;
                case 1:
                    tvProductName = itemView.findViewById(R.id.tvProductName);
                    tvCountValue =itemView.findViewById(R.id.tvCountValue);
                    etRemark = itemView.findViewById(R.id.etRemark);
                    break;
            }
        }
    }
}
