package com.xframework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xframework.delegate.WorksBeltlineDelegate;
import com.xframework.model.BaseWorks;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final int layoutResourceId;
    private final List<BaseWorks> items;
    private final WorksBeltlineDelegate delegate;

    public LeftAdapter(Context mContext, int layoutResourceId, ArrayList<BaseWorks> items, WorksBeltlineDelegate delegate) {
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LeftAdapter.ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(layoutResourceId, parent, false));
        holder.view.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int works_id = (int)holder.tvName.getTag();
                delegate.WorksClick(works_id);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LeftAdapter.ViewHolder myViewHolder = (LeftAdapter.ViewHolder) holder;
        BaseWorks item = items.get(position);

        myViewHolder.tvName.setText(item.getWorks_name());
        myViewHolder.tvName.setTag(item.getWorks_id());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvName = itemView.findViewById(R.id.tvWorksName);
        }
    }
}
