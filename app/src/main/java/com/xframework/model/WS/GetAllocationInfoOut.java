package com.xframework.model.WS;

import com.xframework.model.BaseAllocation;
import com.xframework.model.BaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllocationInfoOut  extends BaseModel {
    private int status;
    private String msg;
    private List<BaseAllocation> allocations = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BaseAllocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<BaseAllocation> allocations) {
        this.allocations = allocations;
    }
}
