package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class DeleteAllocationCheckDetailIn extends BaseModel {
    private int allocation_id;

    private String device_code;

    private String order_code;

    private int user_id;

    public int getAllocationId() {
        return this.allocation_id;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public String getOrderCode() {
        return this.order_code;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
