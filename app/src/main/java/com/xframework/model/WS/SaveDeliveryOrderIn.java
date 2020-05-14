package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWDeliveryOrderDetail;
import com.xframework.model.PWDeliveryOrderHeader;
import java.util.ArrayList;
import java.util.List;

public class SaveDeliveryOrderIn extends BaseModel {
    private List<PWDeliveryOrderDetail> body = new ArrayList<PWDeliveryOrderDetail>();

    private String device_code;

    private PWDeliveryOrderHeader head = new PWDeliveryOrderHeader();

    private int option_type;

    private int user_id;

    public List<PWDeliveryOrderDetail> getBody() {
        return this.body;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public PWDeliveryOrderHeader getHead() {
        return this.head;
    }

    public int getOptionType() {
        return this.option_type;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setBody(List<PWDeliveryOrderDetail> body) {
        this.body = body;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setHead(PWDeliveryOrderHeader head) {
        this.head = head;
    }

    public void setOptionType(int option_type) {
        this.option_type = option_type;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
