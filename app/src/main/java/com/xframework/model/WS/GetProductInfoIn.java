package com.xframework.model.WS;

import com.xframework.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class GetProductInfoIn extends BaseModel {
    private int user_id;
    private String device_code;
    private List<String> product_ids = new ArrayList<>();

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getDeviceCode() {
        return device_code;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public List<String> getProductIds() {
        return product_ids;
    }

    public void setProductIds(List<String> product_ids) {
        this.product_ids = product_ids;
    }
}
