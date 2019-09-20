package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class GetModulesIn extends BaseModel {
    private int user_id;

    private int framework_id;
    private String device_code;

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }


    public int getFrameworkId() {
        return framework_id;
    }

    public void setFrameworkId(int framework_id) {
        this.framework_id = framework_id;
    }
    public String getDeviceCode() {
        return device_code;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }
}
