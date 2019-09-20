package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.MrpSapOrder;

import java.util.ArrayList;

public class GetSuspendedBatchOrderListOut  extends BaseModel {
    private int status;
    private String msg;
    private ArrayList<MrpSapOrder> order_list = new ArrayList<>();

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

    public ArrayList<MrpSapOrder> getOrderList() {
        return order_list;
    }

    public void setOrderList(ArrayList<MrpSapOrder> order_list) {
        this.order_list = order_list;
    }
}
