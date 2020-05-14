package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.ListItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetGenerateAllDataOut extends BaseModel {
    private Map<String, List<ListItem>> generate_data = new HashMap<String, List<ListItem>>();

    private String msg;

    private int status;

    public Map<String, List<ListItem>> getGenerateData() {
        return this.generate_data;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setGenerateData(Map<String, List<ListItem>> generate_data) {
        this.generate_data = generate_data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
