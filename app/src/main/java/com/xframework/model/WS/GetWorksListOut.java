package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.BaseWorks;

import java.util.ArrayList;
import java.util.List;

public class GetWorksListOut  extends BaseModel {
    private ArrayList<BaseWorks> works_list = new ArrayList<>();

    private String msg;

    private int status;

    public ArrayList<BaseWorks> getWorksList() {
        return this.works_list;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setWorksList(ArrayList<BaseWorks> works_list) {
        this.works_list = works_list;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
