package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWReceivedOrderDetail;
import com.xframework.model.PWReceivedOrderHeader;

import java.util.ArrayList;
import java.util.List;

public class SaveReceivedOrderIn extends BaseModel {
    private int user_id;
    private String device_code;
    private int option_type;

    private PWReceivedOrderHeader head = new PWReceivedOrderHeader();
    private List<PWReceivedOrderDetail> body = new ArrayList<>();

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

    public PWReceivedOrderHeader getHead() {
        return head;
    }

    public void setHead(PWReceivedOrderHeader head) {
        this.head = head;
    }

    public List<PWReceivedOrderDetail> getBody() {
        return body;
    }

    public void setBody(List<PWReceivedOrderDetail> body) {
        this.body = body;
    }
}
