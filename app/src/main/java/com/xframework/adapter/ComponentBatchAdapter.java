package com.xframework.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.delegate.BaseDelegate;
import com.xframework.model.LTDelivery;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;

public class ComponentBatchAdapter  extends RecyclerView.Adapter {

    private final BaseDelegate delegate;

    private ArrayList<LTDelivery> items;

    private final int layoutResourceId;

    private final Context mContext;

    public ComponentBatchAdapter (Context mContext, int layoutResourceId, ArrayList<LTDelivery> items, BaseDelegate delegate) {
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
        this.delegate = delegate;
    }

    public ArrayList<LTDelivery> getItems() {
        return items;
    }

    public void setItems(ArrayList<LTDelivery> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @NonNull
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        ViewHolder myViewHolder = (ViewHolder) holder;
        LTDelivery ltDelivery = this.items.get(position);
        myViewHolder.tvBatchNo.setText(ltDelivery.getBatchCode());
        myViewHolder.etCount.setText(String.valueOf(0));
        myViewHolder.tvComponent.setText(ltDelivery.getComponentCode());
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(delegate.removeBarcodes(items.get(index).getBarcode())) {
                    items.remove(index);
                    this.notify();
                }
                else{
                    Toast.makeText(mContext,"删除失败，请联系管理员",Toast.LENGTH_LONG);
                }
            }
        });
        myViewHolder.etCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(items.size()>0){
                    try{
                        items.get(index).setQty(Float.parseFloat(editable.toString()));
                    }
                    catch (Exception ignored){

                    }
                }
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(layoutResourceId, parent, false));
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnDelete;

        EditText etCount;

        TextView tvBatchNo;

        TextView tvComponent;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvBatchNo = itemView.findViewById(R.id.tvBatchNo);
            this.etCount = itemView.findViewById(R.id.etCount);
            this.tvComponent = itemView.findViewById(R.id.tvComponent);
            this.btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
