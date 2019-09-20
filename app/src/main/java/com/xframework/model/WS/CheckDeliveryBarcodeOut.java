package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWProductBarcode;

import java.util.ArrayList;
import java.util.List;

public class CheckDeliveryBarcodeOut extends BaseModel {
    private int status;
    private String msg;
    private List<PWProductBarcode> barcodes = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PWProductBarcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<PWProductBarcode> barcodes) {
        this.barcodes = barcodes;
    }
}
