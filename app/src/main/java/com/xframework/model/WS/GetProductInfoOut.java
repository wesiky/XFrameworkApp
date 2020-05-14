package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.BaseProduct;
import java.util.ArrayList;
import java.util.List;

public class GetProductInfoOut extends BaseModel {
    private String msg;

    private List<BaseProduct> products = new ArrayList<BaseProduct>();

    private int status;

    public String getMsg() {
        return this.msg;
    }

    public List<BaseProduct> getProducts() {
        return this.products;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setProducts(List<BaseProduct> products) {
        this.products = products;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
