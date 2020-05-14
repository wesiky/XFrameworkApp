package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWReturnOrderDetail;
import com.xframework.model.PWReturnOrderHeader;
import java.util.ArrayList;
import java.util.List;

public class SaveReturnOrderIn extends BaseModel {
    private List<PWReturnOrderDetail> body = new ArrayList<PWReturnOrderDetail>();

    private String device_code;

    private PWReturnOrderHeader head = new PWReturnOrderHeader();

    private int option_type;

    private int user_id;

    public List<PWReturnOrderDetail> getBody() {
        return this.body;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public PWReturnOrderHeader getHead() {
        return this.head;
    }

    public int getOptionType() {
        return this.option_type;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setBody(List<PWReturnOrderDetail> body) {
        this.body = body;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setHead(PWReturnOrderHeader head) {
        this.head = head;
    }

    public void setOptionType(int option_type) {
        this.option_type = option_type;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
