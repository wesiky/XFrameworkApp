package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class SWSpareBarcode extends BaseModel implements Serializable {
    private String allocation_code;

    private int allocation_id;

    private String barcode;

    private String brand;

    private float chang_quantity;

    private Date create_date;

    private String create_user;

    private String description;

    private boolean enable = true;

    private Date last_update_date;

    private String last_update_user;

    private float pcs;

    private float price;

    private String spare_code;

    private int spare_id;

    private String spare_model;

    private String spare_name;

    private Date storage_time;

    private float surplus_quantity;

    private String unit;

    public String getAllocationCode() {
        return this.allocation_code;
    }

    public int getAllocationId() {
        return this.allocation_id;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public String getBrand() {
        return this.brand;
    }

    public float getChangQuantity() {
        return this.chang_quantity;
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

    public float getPcs() {
        return this.pcs;
    }

    public float getPrice() {
        return this.price;
    }

    public String getSpareCode() {
        return this.spare_code;
    }

    public int getSpareId() {
        return this.spare_id;
    }

    public String getSpareModel() {
        return this.spare_model;
    }

    public String getSpareName() {
        return this.spare_name;
    }

    public Date getStorageTime() {
        return this.storage_time;
    }

    public float getSurplusQuantity() {
        return this.surplus_quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setAllocationCode(String allocation_code) {
        this.allocation_code = allocation_code;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setChangQuantity(float chang_quantity) {
        this.chang_quantity = chang_quantity;
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

    public void setPcs(float pcs) {
        this.pcs = pcs;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSpareCode(String spare_code) {
        this.spare_code = spare_code;
    }

    public void setSpareId(int spare_id) {
        this.spare_id = spare_id;
    }

    public void setSpareModel(String spare_model) {
        this.spare_model = spare_model;
    }

    public void setSpareName(String spare_name) {
        this.spare_name = spare_name;
    }

    public void setStorageTime(Date storage_time) {
        this.storage_time = storage_time;
    }

    public void setSurplusQuantity(float surplus_quantity) {
        this.surplus_quantity = surplus_quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
