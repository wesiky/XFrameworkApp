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
import com.xframework.model.SWSpareBarcode;
import com.xframework.xframeworkapp.R;

import java.util.ArrayList;

public class SpareBarcodeAdapter extends RecyclerView.Adapter {
    private final int EMPTY_VIEW = 0;

    private BaseDelegate delegate;

    private ArrayList<SWSpareBarcode> items;

    private int layoutResourceId;

    private Context mContext;

    public SpareBarcodeAdapter(Context mContext, int layoutResourceId, ArrayList<SWSpareBarcode> items, BaseDelegate delegate) {
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
        this.delegate = delegate;
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        ViewHolder myViewHolder = (ViewHolder) holder;
        SWSpareBarcode sWSpareBarcode = this.items.get(position);
        myViewHolder.tvBarcode.setText(sWSpareBarcode.getBarcode());
        myViewHolder.etCount.setText(String.valueOf(sWSpareBarcode.getChangQuantity()));
        myViewHolder.tvSurplusCount.setText("/" + sWSpareBarcode.getSurplusQuantity());
        myViewHolder.tvSpare.setText(sWSpareBarcode.getSpareName() + "|" + sWSpareBarcode.getSpareModel());
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(delegate.removeBarcodes(items.get(index).getBarcode())) {
                    items.remove(index);
                    notifyDataSetChanged();
                }
                else{
                    Toast.makeText(mContext,"条码删除失败，请联系管理员",Toast.LENGTH_LONG);
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
                        items.get(index).setChangQuantity(Float.valueOf(editable.toString()));
                    }
                    catch (Exception e){

                    }
                }
            }
        });
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(layoutResourceId, parent, false));
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnDelete;

        EditText etCount;

        TextView tvBarcode;

        TextView tvSpare;

        TextView tvSurplusCount;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvBarcode = itemView.findViewById(R.id.tvBarcode);
            this.etCount = itemView.findViewById(R.id.etCount);
            this.tvSurplusCount = itemView.findViewById(R.id.tvSurplusCount);
            this.tvSpare = itemView.findViewById(R.id.tvSpare);
            this.btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
