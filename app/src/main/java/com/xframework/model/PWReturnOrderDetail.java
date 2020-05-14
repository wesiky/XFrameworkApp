package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class PWReturnOrderDetail implements Serializable {
    private int allocation_id;

    private Date create_date;

    private String create_user;

    private String description;

    private boolean enable = true;

    private int id;

    private Date last_update_date;

    private String last_update_user;

    private String order_code;

    private String outer_barcode;

    private String product_barcode;

    private int product_id;

    public int getAllocationId() {
        return this.allocation_id;
    }

    public Date getCreateDate() {
        return this.create_date;
    }

    public String getCreateUser() {
        return this.create_user;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getEnable() {
        return this.enable;
    }

    public int getId() {
        return this.id;
    }

    public Date getLastUpdateDate() {
        return this.last_update_date;
    }

    public String getLastUpdateUser() {
        return this.last_update_user;
    }

    public String getOrderCode() {
        return this.order_code;
    }

    public String getOuterBarcode() {
        return this.outer_barcode;
    }

    public String getProductBarcode() {
        return this.product_barcode;
    }

    public int getProductId() {
        return this.product_id;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }

    public void setCreateUser(String create_user) {
        this.create_user = create_user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }

    public void setOuterBarcode(String outer_barcode) {
        this.outer_barcode = outer_barcode;
    }

    public void setProductBarcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }
}
