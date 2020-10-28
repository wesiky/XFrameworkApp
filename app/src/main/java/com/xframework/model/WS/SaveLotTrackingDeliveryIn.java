package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.LTDelivery;

import java.util.ArrayList;
import java.util.List;

public class SaveLotTrackingDeliveryIn  extends BaseModel {
    private List<LTDelivery> barcodes = new ArrayList<>();

    private String device_code;

    private int user_id;

    public List<LTDelivery> getBarcodes() {
        return this.barcodes;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setBarcodes(List<LTDelivery> barcodes) {
        this.barcodes = barcodes;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
