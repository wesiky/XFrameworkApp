package com.xframework.model.WS;

import com.xframework.model.BaseAllocation;
import com.xframework.model.BaseModel;
import java.util.ArrayList;

public class GetCheckAllocationListOut extends BaseModel {
    private ArrayList<BaseAllocation> allocations = new ArrayList<BaseAllocation>();

    private String msg;

    private int status;

    public ArrayList<BaseAllocation> getAllocations() {
        return this.allocations;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setAllocations(ArrayList<BaseAllocation> allocations) {
        this.allocations = allocations;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
