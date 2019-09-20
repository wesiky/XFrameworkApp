package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWDeliveryOrderDetail;
import com.xframework.model.PWDeliveryOrderHeader;

import java.util.ArrayList;
import java.util.List;

public class SaveDeliveryOrderIn extends BaseModel {
    private int user_id;
    private String device_code;
    private int option_type;

    private PWDeliveryOrderHeader head = new PWDeliveryOrderHeader();
    private List<PWDeliveryOrderDetail> body = new ArrayList<>();

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

    public PWDeliveryOrderHeader getHead() {
        return head;
    }

    public void setHead(PWDeliveryOrderHeader head) {
        this.head = head;
    }

    public List<PWDeliveryOrderDetail> getBody() {
        return body;
    }

    public void setBody(List<PWDeliveryOrderDetail> body) {
        this.body = body;
    }
}
