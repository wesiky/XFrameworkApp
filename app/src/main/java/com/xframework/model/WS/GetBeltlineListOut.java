package com.xframework.model.WS;

import com.xframework.model.BaseBeltline;
import com.xframework.model.BaseModel;
import com.xframework.model.BaseWorks;

import java.util.ArrayList;
import java.util.List;

public class GetBeltlineListOut extends BaseModel {
    private ArrayList<BaseBeltline> beltline_list = new ArrayList<>();

    private String msg;

    private int status;

    public ArrayList<BaseBeltline> getBeltlineList() {
        return this.beltline_list;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setBeltlineList(ArrayList<BaseBeltline> beltline_list) {
        this.beltline_list = beltline_list;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
