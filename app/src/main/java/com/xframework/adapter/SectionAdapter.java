package com.xframework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xframework.delegate.BatchDeliveryDelegate;
import com.xframework.item.BaseSection;
import com.xframework.item.BatchItem;
import com.xframework.item.ProductItem;
import com.xframework.xframeworkapp.R;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<BaseSection> mList;
    private BatchDeliveryDelegate delegate;

    public SectionAdapter(Context mContext, List<BaseSection> mList,BatchDeliveryDelegate delegate) {
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


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:
                final ViewHolder holder0 = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_batch, parent, false), viewType);
                holder0.tvDelivery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder0.getAdapterPosition();
                        delegate.DeliveryBatch(((BatchItem) mList.get(position).getTag()).getBatchNo(),position);

                    }
                });
                holder0.tvSuspended.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder0.getAdapterPosition();
                        delegate.SuspendedBatch(((BatchItem) mList.get(position).getTag()).getBatchNo(),position);
                    }
                });
                return holder0;
            case 1:
                return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false), viewType);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder myViewHolder = (ViewHolder) holder;
        switch(myViewHolder.viewType){
            case 0:
                BatchItem itemBatch = (BatchItem)mList.get(position).getTag();
                myViewHolder.tvBatchValue.setText(itemBatch.getBatchNo());
                break;
            case 1:
                ProductItem itemProduct = (ProductItem)mList.get(position).getTag();
                myViewHolder.tvProductName.setText(itemProduct.getProductName());
                myViewHolder.tvCountValue.setText(String.valueOf(itemProduct.getQuantity()));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

//    // 接口回调
//    public interface OnMyItemClickListener{
//        void onMyItemClick(RecyclerView parent, View view, int position, String data);
//    }
//    //进行删除
//    public void remove(int position) {
//        data.remove(position);
//        //notifyDataSetChanged();// 提醒list刷新，没有动画效果
//        notifyItemRemoved(position); // 提醒item删除指定数据，这里有RecyclerView的动画效果
//    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvBatchValue;
        TextView tvSuspended;
        TextView tvDelivery;
        TextView tvProductName;
        TextView tvProductCode;
        TextView tvCountValue;
        int viewType;
        public ViewHolder(View itemView,int viewType) {
            super(itemView);
            this.viewType = viewType;
            switch(viewType){
                case 0:
                    tvBatchValue = (TextView) itemView.findViewById(R.id.tvBatchValue);
                    tvSuspended = (TextView) itemView.findViewById(R.id.tvLackAttachment);
                    tvDelivery = (TextView) itemView.findViewById(R.id.tvLackBody);
                    break;
                case 1:
                    tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                    tvProductCode = (TextView) itemView.findViewById(R.id.tvProductCode);
                    tvCountValue = (TextView) itemView.findViewById(R.id.tvCountValue);
                    break;
            }
        }
    }
}
