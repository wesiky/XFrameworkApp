package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class GetOrderCodeOut extends BaseModel {
    private String msg;

    private String order_code;

    private int status;

    public String getMsg() {
        return this.msg;
    }

    public String getOrderCode() {
        return this.order_code;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
