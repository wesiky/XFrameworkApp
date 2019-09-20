package com.xframework.item;

import java.io.Serializable;

public class BatchProductItem implements Serializable {
    private String product_code;
    private String product_name;
    private int body_product_id;
    private String body_product_code;
    private String body_product_name;
    private int quantity = 0;
    private int batch_quantity;
    private boolean add = true;

    public String getProductCode() {
        return product_code;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public int getBodyProductId() {
        return body_product_id;
    }

    public void setBodyProductId(int body_product_id) {
        this.body_product_id = body_product_id;
    }

    public String getBodyProductCode() {
        return body_product_code;
    }

    public void setBodyProductCode(String body_product_code) {
        this.body_product_code = body_product_code;
    }

    public String getBodyProductName() {
        return body_product_name;
    }

    public void setBodyProductName(String body_product_name) {
        this.body_product_name = body_product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBatchQuantity() {
        return batch_quantity;
    }

    public void setBatchQuantity(int batch_quantity) {
        this.batch_quantity = batch_quantity;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
}
