package com.xframework.model.WSChint;

import com.xframework.model.PWProductBarcode;
import java.util.ArrayList;
import java.util.List;

public class GetBarcodeOut {
    private List<PWProductBarcode> barcodes = new ArrayList<PWProductBarcode>();

    private String msg;

    private int status;

    public List<PWProductBarcode> getBarcodes() {
        return this.barcodes;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
