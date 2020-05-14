package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class GetModulesIn extends BaseModel {
    private String device_code;

    private int framework_id;

    private int user_id;

    public String getDeviceCode() {
        return this.device_code;
    }

    public int getFrameworkId() {
        return this.framework_id;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setFrameworkId(int framework_id) {
        this.framework_id = framework_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
