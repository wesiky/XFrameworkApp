package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWProductMoveOrderDetail;
import com.xframework.model.PWProductMoveOrderHeader;
import com.xframework.model.PWReceivedOrderDetail;
import com.xframework.model.PWReceivedOrderHeader;

import java.util.ArrayList;
import java.util.List;

public class SaveMoveOrderIn extends BaseModel {
    private int user_id;
    private String device_code;
    private int option_type;

    private PWProductMoveOrderHeader head = new PWProductMoveOrderHeader();
    private List<PWProductMoveOrderDetail> body = new ArrayList<>();

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

    public PWProductMoveOrderHeader getHead() {
        return head;
    }

    public void setHead(PWProductMoveOrderHeader head) {
        this.head = head;
    }

    public List<PWProductMoveOrderDetail> getBody() {
        return body;
    }

    public void setBody(List<PWProductMoveOrderDetail> body) {
        this.body = body;
    }
}
