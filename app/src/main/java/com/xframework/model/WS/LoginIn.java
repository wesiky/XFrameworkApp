package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class LoginIn extends BaseModel {
    private String device_code;

    private String password;

    private String user_name;

    public String getDeviceCode() {
        return this.device_code;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.user_name;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }
}
