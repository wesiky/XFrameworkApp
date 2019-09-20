package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWDeliveryOrderDetail;
import com.xframework.model.PWDeliveryOrderHeader;

import java.util.ArrayList;
import java.util.List;

public class SaveDeliveryBatchOrderIn extends BaseModel {
    private int user_id;
    private String device_code;
    private int option_type;
    private String frame_barcode;
    private String batch_no;

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

    public String getFrameBarcode() {
        return frame_barcode;
    }

    public void setFrameBarcode(String frame_barcode) {
        this.frame_barcode = frame_barcode;
    }

    public String getBatchNo() {
        return batch_no;
    }

    public void setBatchNo(String batch_no) {
        this.batch_no = batch_no;
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
