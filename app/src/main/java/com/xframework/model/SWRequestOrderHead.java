package com.xframework.model;

public class SWRequestOrderHead extends BaseOrderHead {
    private String request_user;

    public String getRequestUser() {
        return this.request_user;
    }

    public void setRequestUser(String request_user) {
        this.request_user = request_user;
    }
}
