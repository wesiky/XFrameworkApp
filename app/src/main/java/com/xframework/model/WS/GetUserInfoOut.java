package com.xframework.model.WS;

import com.xframework.model.LoginUserInfo;
import com.xframework.model.UserInfo;

public class GetUserInfoOut {
    private int status;
    private String msg;
    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

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

}
