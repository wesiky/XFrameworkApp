package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class GetProductInfoIn extends BaseModel {
    private String device_code;

    private List<String> product_ids = new ArrayList<String>();

    private int user_id;

    public String getDeviceCode() {
        return this.device_code;
    }

    public List<String> getProductIds() {
        return this.product_ids;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setProductIds(List<String> product_ids) {
        this.product_ids = product_ids;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
