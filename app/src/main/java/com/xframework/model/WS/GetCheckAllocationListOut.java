package com.xframework.model.WS;

import com.xframework.model.BaseAllocation;
import com.xframework.model.BaseModel;
import com.xframework.model.PWProductBarcode;

import java.util.ArrayList;
import java.util.List;

public class GetCheckAllocationListOut extends BaseModel {
    private int status;
    private String msg;
    private ArrayList<BaseAllocation> allocations = new ArrayList<>();

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

    public ArrayList<BaseAllocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(ArrayList<BaseAllocation> allocations) {
        this.allocations = allocations;
    }
}
