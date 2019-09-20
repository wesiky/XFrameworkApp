package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class GetOrderCodeIn extends BaseModel {
    private int user_id;
    private int type;
    private String device_code;

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDeviceCode() {
        return device_code;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }
}
