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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SpareReceivedBarcodeAdapter extends RecyclerView.Adapter {
    private BaseDelegate delegate;

    private ArrayList<SWSpareBarcode> items;

    private int layoutResourceId;

    private Context mContext;

    public SpareReceivedBarcodeAdapter(Context mContext, int layoutResourceId, ArrayList<SWSpareBarcode> items, BaseDelegate delegate) {
        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
        this.delegate = delegate;
    }

    public int getItemCount() {
        return items.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();
        ViewHolder myViewHolder = (ViewHolder) holder;
        SWSpareBarcode sWSpareBarcode = items.get(index);
        myViewHolder.tvBarcode.setText(sWSpareBarcode.getBarcode());
        myViewHolder.etCount.setText(String.valueOf(sWSpareBarcode.getPcs()));
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
                if (items.size() > 0) {
                    try {
                        items.get(index).setPcs(Float.parseFloat(editable.toString()));
                        items.get(index).setSurplusQuantity(Float.parseFloat(editable.toString()));
                        items.get(index).setChangQuantity(Float.parseFloat(editable.toString()));
                    } catch (Exception exception) {
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

        public ViewHolder(View param1View) {
            super(param1View);
            this.tvBarcode = param1View.findViewById(R.id.tvBarcode);
            this.etCount = param1View.findViewById(R.id.etCount);
            this.tvSpare = param1View.findViewById(R.id.tvSpare);
            this.btnDelete = param1View.findViewById(R.id.btnDelete);
        }
    }
}
