package com.xframework.model.WS;

import com.xframework.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class GetAllocationInfoIn extends BaseModel {
    private int user_id;
    private String device_code;
    private List<String> allocation_codes = new ArrayList<>();

    public List<String> getAllocationCodes() {
        return allocation_codes;
    }

    public void setAllocationCodes(List<String> allocation_codes) {
        this.allocation_codes = allocation_codes;
    }

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
}
