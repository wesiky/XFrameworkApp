package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWReceivedOrderDetail;
import com.xframework.model.PWReceivedOrderHeader;
import java.util.ArrayList;
import java.util.List;

public class SaveReceivedOrderIn extends BaseModel {
    private List<PWReceivedOrderDetail> body = new ArrayList<PWReceivedOrderDetail>();

    private String device_code;

    private PWReceivedOrderHeader head = new PWReceivedOrderHeader();

    private int option_type;

    private int user_id;

    public List<PWReceivedOrderDetail> getBody() {
        return this.body;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public PWReceivedOrderHeader getHead() {
        return this.head;
    }

    public int getOptionType() {
        return this.option_type;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setBody(List<PWReceivedOrderDetail> body) {
        this.body = body;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setHead(PWReceivedOrderHeader head) {
        this.head = head;
    }

    public void setOptionType(int option_type) {
        this.option_type = option_type;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
