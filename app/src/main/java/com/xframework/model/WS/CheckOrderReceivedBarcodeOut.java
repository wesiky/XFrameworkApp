package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWProductBarcode;
import java.util.ArrayList;
import java.util.List;

public class CheckOrderReceivedBarcodeOut extends BaseModel {
    private List<PWProductBarcode> barcodes = new ArrayList<PWProductBarcode>();

    private String description;

    private String msg;

    private int status;

    public List<PWProductBarcode> getBarcodes() {
        return this.barcodes;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setBarcodes(List<PWProductBarcode> barcodes) {
        this.barcodes = barcodes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
