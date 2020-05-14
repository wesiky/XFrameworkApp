package com.xframework.model;

import java.util.Date;

public class BaseOrderBarcode extends BaseModel {
    private int allocation_id;

    private String barcode;

    private int body_id;

    private String brand;

    private Date create_date;

    private String create_user;

    private String description;

    private boolean enable = true;

    private int id;

    private Date last_update_date;

    private String last_update_user;

    private float pcs;

    private float price;

    private float quantity;

    private int spare_id;

    private float surplus_quantity;

    private String unit;

    public int getAllocationId() {
        return this.allocation_id;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public int getBodyId() {
        return this.body_id;
    }

    public String getBrand() {
        return this.brand;
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

    public float getPcs() {
        return this.pcs;
    }

    public float getPrice() {
        return this.price;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public int getSpareId() {
        return this.spare_id;
    }

    public float getSurplusQuantity() {
        return this.surplus_quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setBodyId(int body_id) {
        this.body_id = body_id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public void setPcs(float pcs) {
        this.pcs = pcs;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setSpareId(int spare_id) {
        this.spare_id = spare_id;
    }

    public void setSurplusQuantity(float surplus_quantity) {
        this.surplus_quantity = surplus_quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
