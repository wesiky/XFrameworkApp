package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class CheckFrameBarcodeIn extends BaseModel {
    private String barcode;

    private String device_code;

    private int user_id;

    public String getBarcode() {
        return this.barcode;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
