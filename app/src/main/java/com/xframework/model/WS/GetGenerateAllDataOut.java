package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.ListItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetGenerateAllDataOut extends BaseModel {
    private int status;
    private String msg;
    private Map<String, List<ListItem>> generate_data = new HashMap<>();

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

    public Map<String, List<ListItem>> getGenerateData() {
        return generate_data;
    }

    public void setGenerateData(Map<String, List<ListItem>> map_setting) {
        this.generate_data = map_setting;
    }
}
