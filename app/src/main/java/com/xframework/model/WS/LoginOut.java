package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class LoginOut extends BaseModel {
    String msg;

    int status;

    int user_id;

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
