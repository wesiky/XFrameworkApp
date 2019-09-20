package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWReturnOrderDetail;
import com.xframework.model.PWReturnOrderHeader;

import java.util.ArrayList;
import java.util.List;

public class SaveReturnOrderIn extends BaseModel {
    private int user_id;
    private String device_code;
    private int option_type;

    private PWReturnOrderHeader head = new PWReturnOrderHeader();
    private List<PWReturnOrderDetail> body = new ArrayList<>();

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getDeviceCode() {
        return device_code;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public int getOptionType() {
        return option_type;
    }

    public void setOptionType(int option_type) {
        this.option_type = option_type;
    }

    public PWReturnOrderHeader getHead() {
        return head;
    }

    public void setHead(PWReturnOrderHeader head) {
        this.head = head;
    }

    public List<PWReturnOrderDetail> getBody() {
        return body;
    }

    public void setBody(List<PWReturnOrderDetail> body) {
        this.body = body;
    }
}
