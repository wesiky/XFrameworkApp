package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class SWSpareMove extends BaseModel implements Serializable {
    private String barcode;

    private Date create_date;

    private String create_user;

    private String description;

    private boolean enable = true;

    private int id;

    private int in_allocation_id;

    private Date last_update_date;

    private String last_update_user;

    private int out_allocation_id;

    public String getBarcode() {
        return this.barcode;
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

    public int getInAllocationId() {
        return this.in_allocation_id;
    }

    public Date getLastUpdateDate() {
        return this.last_update_date;
    }

    public String getLastUpdateUser() {
        return this.last_update_user;
    }

    public int getOutAllocationId() {
        return this.out_allocation_id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public void setInAllocationId(int in_allocation_id) {
        this.in_allocation_id = in_allocation_id;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public void setOutAllocationId(int out_allocation_id) {
        this.out_allocation_id = out_allocation_id;
    }
}
