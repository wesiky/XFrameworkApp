package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWProductBarcode;

import java.util.ArrayList;
import java.util.List;

public class CheckOrderReceivedBarcodeOut extends BaseModel {
    private int status;
    private String msg;
    private String description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PWProductBarcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<PWProductBarcode> barcodes) {
        this.barcodes = barcodes;
    }
}
