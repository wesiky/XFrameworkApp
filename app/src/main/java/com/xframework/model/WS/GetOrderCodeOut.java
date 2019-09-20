package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class GetOrderCodeOut extends BaseModel {
    private int status;
    private String msg;
    private String order_code;

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

    public String getOrderCode() {
        return order_code;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }
}
