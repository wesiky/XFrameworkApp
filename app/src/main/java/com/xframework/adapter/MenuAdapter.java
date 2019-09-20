package com.xframework.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xframework.model.Module;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;


public class MenuAdapter extends ArrayAdapter<Module> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Module> mGridData = new ArrayList<Module>();


    public MenuAdapter(Context context, int resource, ArrayList<Module> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.mGridData = objects;
    }

    public void setGridData(ArrayList<Module> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_name);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_ico);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Module item = mGridData.get(position);
        holder.textView.setText(item.getModuleName());
// 拿到图片ID
        int icon = mContext.getResources().getIdentifier(item.getIcon(), "drawable", mContext.getPackageName());
// 设置图片
        holder.imageView.setImageResource(icon);
        //Picasso.with(mContext).load(item.getImage()).into(holder.imageView);
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
