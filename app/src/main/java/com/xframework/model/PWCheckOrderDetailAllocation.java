package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class PWCheckOrderDetailAllocation extends BaseModel implements Serializable {
    private int check_allocation_id;

    private Date create_date;

    private String create_user;

    private String description;

    private boolean enable = true;

    private Date last_update_date;

    private String last_update_user;

    private int new_allocation_id;

    private int old_allocation_id;

    private String order_code;

    private String outer_barcode;

    private String product_barcode;

    private String product_code;

    private int product_id;

    private String product_name;

    private int status;

    public int getCheckAllocationId() {
        return this.check_allocation_id;
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

    public Date getLastUpdateDate() {
        return this.last_update_date;
    }

    public String getLastUpdateUser() {
        return this.last_update_user;
    }

    public int getNewAllocationId() {
        return this.new_allocation_id;
    }

    public int getOldAllocationId() {
        return this.old_allocation_id;
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

    public String getProductCode() {
        return this.product_code;
    }

    public int getProductId() {
        return this.product_id;
    }

    public String getProductName() {
        return this.product_name;
    }

    public int getStatus() {
        return this.status;
    }

    public void setCheckAllocationId(int check_allocation_id) {
        this.check_allocation_id = check_allocation_id;
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

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public void setNewAllocationId(int new_allocation_id) {
        this.new_allocation_id = new_allocation_id;
    }

    public void setOldAllocationId(int old_allocation_id) {
        this.old_allocation_id = old_allocation_id;
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

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
