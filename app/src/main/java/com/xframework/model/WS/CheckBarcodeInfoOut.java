package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.BaseProduct;

import java.util.ArrayList;
import java.util.List;

public class CheckBarcodeInfoOut extends BaseModel {
    private int status;
    private String msg;
    private BaseProduct product;
    private List<String> barcodes = new ArrayList<>();

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

    public BaseProduct getProduct() {
        return product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public List<String> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<String> barcodes) {
        this.barcodes = barcodes;
    }
}
