package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWProductMoveOrderDetail;
import com.xframework.model.PWProductMoveOrderHeader;
import java.util.ArrayList;
import java.util.List;

public class SaveMoveOrderIn extends BaseModel {
    private List<PWProductMoveOrderDetail> body = new ArrayList<PWProductMoveOrderDetail>();

    private String device_code;

    private PWProductMoveOrderHeader head = new PWProductMoveOrderHeader();

    private int option_type;

    private int user_id;

    public List<PWProductMoveOrderDetail> getBody() {
        return this.body;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public PWProductMoveOrderHeader getHead() {
        return this.head;
    }

    public int getOptionType() {
        return this.option_type;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setBody(List<PWProductMoveOrderDetail> body) {
        this.body = body;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setHead(PWProductMoveOrderHeader head) {
        this.head = head;
    }

    public void setOptionType(int option_type) {
        this.option_type = option_type;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
