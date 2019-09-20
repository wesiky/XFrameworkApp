package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.BaseProduct;

public class GetProductInfoByCodeOut extends BaseModel {
    private int status;
    private String msg;
    private BaseProduct product;

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
}
