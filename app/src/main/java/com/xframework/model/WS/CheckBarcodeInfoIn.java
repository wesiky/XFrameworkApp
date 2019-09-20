package com.xframework.model.WS;

import com.xframework.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class CheckBarcodeInfoIn extends BaseModel {
    private int user_id;
    private String product_code;
    private List<String> barcodes = new ArrayList<>();
    private String device_code;

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getProductCode() {
        return product_code;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public List<String> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<String> barcodes) {
        this.barcodes = barcodes;
    }

    public String getDeviceCode() {
        return device_code;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }
}
