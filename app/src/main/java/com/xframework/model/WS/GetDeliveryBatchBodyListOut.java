package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.MrpSapOrder;
import java.util.ArrayList;

public class GetDeliveryBatchBodyListOut extends BaseModel {
    private String msg;

    private ArrayList<MrpSapOrder> order_list = new ArrayList<MrpSapOrder>();

    private int status;

    public String getMsg() {
        return this.msg;
    }

    public ArrayList<MrpSapOrder> getOrderList() {
        return this.order_list;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setOrderList(ArrayList<MrpSapOrder> order_list) {
        this.order_list = order_list;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
