package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.BaseProduct;

public class GetProductInfoByCodeOut extends BaseModel {
    private String msg;

    private BaseProduct product;

    private int status;

    public String getMsg() {
        return this.msg;
    }

    public BaseProduct getProduct() {
        return this.product;
    }

    public int getStatus() {
        return this.status;
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
