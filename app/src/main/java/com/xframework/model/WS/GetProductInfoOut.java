package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.BaseProduct;

import java.util.ArrayList;
import java.util.List;

public class GetProductInfoOut extends BaseModel {
    private int status;
    private String msg;
    private List<BaseProduct> products = new ArrayList<>();

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

    public List<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(List<BaseProduct> products) {
        this.products = products;
    }
}
