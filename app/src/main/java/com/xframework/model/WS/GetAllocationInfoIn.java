package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class GetAllocationInfoIn extends BaseModel {
    private List<String> allocation_codes = new ArrayList<String>();

    private String device_code;

    private int user_id;

    public List<String> getAllocationCodes() {
        return this.allocation_codes;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setAllocationCodes(List<String> allocation_codes) {
        this.allocation_codes = allocation_codes;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
