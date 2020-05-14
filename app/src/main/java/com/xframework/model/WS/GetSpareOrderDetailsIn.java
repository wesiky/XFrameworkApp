package com.xframework.model.WS;

public class GetSpareOrderDetailsIn extends BaseWSIn {
    private String order_code;

    public String getOrderCode() {
        return this.order_code;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }
}
