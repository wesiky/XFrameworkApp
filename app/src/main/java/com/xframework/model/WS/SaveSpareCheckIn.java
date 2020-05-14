package com.xframework.model.WS;

import com.xframework.model.BaseOrderBarcode;
import java.util.ArrayList;
import java.util.List;

public class SaveSpareCheckIn extends BaseWSIn {
    private List<BaseOrderBarcode> barcodes = new ArrayList<BaseOrderBarcode>();

    private String order_code;

    public List<BaseOrderBarcode> getBarcodes() {
        return this.barcodes;
    }

    public String getOrderCode() {
        return this.order_code;
    }

    public void setBarcodes(List<BaseOrderBarcode> barcodes) {
        this.barcodes = barcodes;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }
}
