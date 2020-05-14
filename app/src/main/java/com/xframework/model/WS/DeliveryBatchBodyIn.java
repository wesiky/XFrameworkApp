package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWDeliveryOrderDetail;
import com.xframework.model.PWDeliveryOrderHeader;
import java.util.ArrayList;
import java.util.List;

public class DeliveryBatchBodyIn extends BaseModel {
    private String batch_no;

    private List<PWDeliveryOrderDetail> body = new ArrayList<PWDeliveryOrderDetail>();

    private String device_code;

    private String frame_barcode;

    private PWDeliveryOrderHeader head = new PWDeliveryOrderHeader();

    private int option_type;

    private int user_id;

    public String getBatchNo() {
        return this.batch_no;
    }

    public List<PWDeliveryOrderDetail> getBody() {
        return this.body;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public String getFrameBarcode() {
        return this.frame_barcode;
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

    public void setBatchNo(String batch_no) {
        this.batch_no = batch_no;
    }

    public void setBody(List<PWDeliveryOrderDetail> body) {
        this.body = body;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setFrameBarcode(String frame_barcode) {
        this.frame_barcode = frame_barcode;
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
