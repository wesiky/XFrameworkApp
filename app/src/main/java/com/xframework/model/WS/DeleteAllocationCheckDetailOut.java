package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class DeleteAllocationCheckDetailOut extends BaseModel {
    private String msg;

    private int status;

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
