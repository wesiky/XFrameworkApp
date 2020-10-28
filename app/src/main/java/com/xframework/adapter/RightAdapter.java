package com.xframework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.delegate.WorksBeltlineDelegate;
import com.xframework.model.BaseBeltline;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final int layoutResourceId;

    private List<BaseBeltline> items = new ArrayList<>();
    private final WorksBeltlineDelegate delegate;

    public RightAdapter(Context mContext, int layoutResourceId, WorksBeltlineDelegate delegate) {
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RightAdapter.ViewHolder holder = new RightAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(layoutResourceId, parent, false));
        holder.view.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int beltline_id = (int)holder.tvName.getTag();
                String beltline_name = holder.tvName.getText().toString();
                delegate.BeltlineClick(beltline_id,beltline_name);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myViewHolder = (ViewHolder) holder;
        BaseBeltline item = items.get(position);

        myViewHolder.tvName.setText(item.getBeltline_name());
        myViewHolder.tvName.setTag(item.getBeltline_id());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<BaseBeltline> getItems() {
        return items;
    }

    public void setItems(List<BaseBeltline> items) {
        this.items = items;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvName = itemView.findViewById(R.id.tvBeltlineName);
        }
    }
}
