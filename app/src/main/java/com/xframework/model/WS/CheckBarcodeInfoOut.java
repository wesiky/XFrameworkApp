package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.BaseProduct;
import com.xframework.model.PWProductBarcode;
import java.util.ArrayList;
import java.util.List;

public class CheckBarcodeInfoOut extends BaseModel {
    private List<PWProductBarcode> barcodes = new ArrayList<PWProductBarcode>();

    private String msg;

    private BaseProduct product;

    private int status;

    public List<PWProductBarcode> getBarcodes() {
        return this.barcodes;
    }

    public String getMsg() {
        return this.msg;
    }

    public BaseProduct getProduct() {
        return this.product;
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

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
