package com.xframework.model.WS;

import com.xframework.model.UserInfo;

public class GetUserInfoOut {
    private UserInfo data;

    private String msg;

    private int status;

    public UserInfo getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
