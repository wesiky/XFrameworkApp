package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class GetOrderCodeIn extends BaseModel {
    private String device_code;

    private int type;

    private int user_id;

    public String getDeviceCode() {
        return this.device_code;
    }

    public int getType() {
        return this.type;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
