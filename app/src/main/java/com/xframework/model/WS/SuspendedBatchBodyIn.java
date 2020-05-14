package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class SuspendedBatchBodyIn extends BaseModel {
    private String batch_no;

    private String device_code;

    private int user_id;

    public String getBatchNo() {
        return this.batch_no;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setBatchNo(String batch_no) {
        this.batch_no = batch_no;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
