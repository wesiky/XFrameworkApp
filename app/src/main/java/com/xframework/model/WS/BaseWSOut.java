package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class BaseWSOut extends BaseModel {
    private String msg = "执行成功";

    private int status = 0;

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
