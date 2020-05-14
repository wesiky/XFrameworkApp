package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.UserInfo;
import java.util.ArrayList;
import java.util.List;

public class GetRepairmansOut extends BaseModel {
    private String msg;

    private List<UserInfo> repairmans = new ArrayList<UserInfo>();

    private int status;

    public String getMsg() {
        return this.msg;
    }

    public List<UserInfo> getRepairmans() {
        return this.repairmans;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRepairmans(List<UserInfo> repairmans) {
        this.repairmans = repairmans;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
