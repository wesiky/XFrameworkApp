package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWCheckOrderHeader;
import java.util.ArrayList;

public class GetCheckOrderListOut extends BaseModel {
    private String msg;

    private ArrayList<PWCheckOrderHeader> order_list = new ArrayList<PWCheckOrderHeader>();

    private int status;

    public String getMsg() {
        return this.msg;
    }

    public ArrayList<PWCheckOrderHeader> getOrderList() {
        return this.order_list;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setOrderList(ArrayList<PWCheckOrderHeader> order_list) {
        this.order_list = order_list;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
