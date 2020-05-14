package com.xframework.adapter;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.delegate.BatchDeliveryDelegate;
import com.xframework.item.BaseSection;
import com.xframework.item.BatchItem;
import com.xframework.item.BatchProductItem;
import com.xframework.item.ProductItem;
import com.xframework.model.MrpSapOrder;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;
import java.util.List;

public class BatchPendAdapter  extends RecyclerView.Adapter {
    private Context mContext;
    private List<MrpSapOrder> mList;

    public BatchPendAdapter(Context mContext, List<MrpSapOrder> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BatchPendAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_batch_pend, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int index = position;
        final BatchPendAdapter.ViewHolder myViewHolder = (BatchPendAdapter.ViewHolder) holder;
        MrpSapOrder item = mList.get(position);
        myViewHolder.tvProductName.setText(item.getProductName());
        myViewHolder.tvCountValue.setText(String.valueOf(item.getCount()));
        myViewHolder.etRemark.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                mList.get(index).setRemark(myViewHolder.etRemark.getText().toString());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvProductName;
        TextView tvCountValue;
        EditText etRemark;

        public ViewHolder(View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvCountValue = itemView.findViewById(R.id.tvCountValue);
            etRemark = itemView.findViewById(R.id.etRemark);
        }
    }
}
