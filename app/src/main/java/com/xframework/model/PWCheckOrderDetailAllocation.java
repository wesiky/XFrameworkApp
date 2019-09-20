package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class PWCheckOrderDetailAllocation  extends BaseModel implements Serializable {
    private String order_code;
    private int check_allocation_id;
    private String product_barcode;
    private int status;
    private int old_allocation_id;
    private int new_allocation_id;
    private String description;
    private Date create_date;
    private String create_user;
    private Date last_update_date;
    private String last_update_user;
    private boolean enable = true;
    private String outer_barcode;
    private int product_id;
    private String product_code;
    private String product_name;

    public String getOrderCode() {
        return order_code;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }

    public int getCheckAllocationId() {
        return check_allocation_id;
    }

    public void setCheckAllocationId(int check_allocation_id) {
        this.check_allocation_id = check_allocation_id;
    }

    public String getProductBarcode() {
        return product_barcode;
    }

    public void setProductBarcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOldAllocationId() {
        return old_allocation_id;
    }

    public void setOldAllocationId(int old_allocation_id) {
        this.old_allocation_id = old_allocation_id;
    }

    public int getNewAllocationId() {
        return new_allocation_id;
    }

    public void setNewAllocationId(int new_allocation_id) {
        this.new_allocation_id = new_allocation_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return create_date;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreateUser() {
        return create_user;
    }

    public void setCreateUser(String create_user) {
        this.create_user = create_user;
    }

    public Date getLastUpdateDate() {
        return last_update_date;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public String getLastUpdateUser() {
        return last_update_user;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getOuterBarcode() {
        return outer_barcode;
    }

    public void setOuterBarcode(String outer_barcode) {
        this.outer_barcode = outer_barcode;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

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
}
