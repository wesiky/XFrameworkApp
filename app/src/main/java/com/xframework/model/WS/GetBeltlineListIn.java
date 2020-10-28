package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class GetBeltlineListIn extends BaseModel {
    private String device_code;

    private int user_id;

    private int works_id;

    public String getDeviceCode() {
        return this.device_code;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getWorksId() {
        return works_id;
    }

    public void setWorksId(int works_id) {
        this.works_id = works_id;
    }
}
