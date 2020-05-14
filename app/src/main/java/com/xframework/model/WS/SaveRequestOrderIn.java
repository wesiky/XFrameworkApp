package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWRequestOrderDetail;
import com.xframework.model.PWRequestOrderHeader;
import java.util.ArrayList;
import java.util.List;

public class SaveRequestOrderIn extends BaseModel {
    private List<PWRequestOrderDetail> body = new ArrayList<PWRequestOrderDetail>();

    private String device_code;

    private PWRequestOrderHeader head = new PWRequestOrderHeader();

    private int option_type;

    private int user_id;

    public List<PWRequestOrderDetail> getBody() {
        return this.body;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public PWRequestOrderHeader getHead() {
        return this.head;
    }

    public int getOptionType() {
        return this.option_type;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setBody(List<PWRequestOrderDetail> body) {
        this.body = body;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setHead(PWRequestOrderHeader head) {
        this.head = head;
    }

    public void setOptionType(int option_type) {
        this.option_type = option_type;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
