package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWCheckOrderHeader;
import com.xframework.model.PWProductBarcode;

import java.util.ArrayList;
import java.util.List;

public class GetCheckOrderListOut extends BaseModel {
    private int status;
    private String msg;
    private ArrayList<PWCheckOrderHeader> order_list = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<PWCheckOrderHeader> getOrderList() {
        return order_list;
    }

    public void setOrderList(ArrayList<PWCheckOrderHeader> order_list) {
        this.order_list = order_list;
    }
}
