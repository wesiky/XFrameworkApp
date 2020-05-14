package com.xframework.model;

import java.io.Serializable;

public class SpareItem implements Serializable {
    private String description;

    private float quantity;

    private String spare_code;

    private int spare_id;

    private String spare_model;

    private String spare_name;

    private float sum_quantity;

    public String getDescription() {
        return this.description;
    }

    public float getQuantity() {
        return this.quantity;
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

    public float getSumQuantity() {
        return this.sum_quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
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

    public void setSumQuantity(float sum_quantity) {
        this.sum_quantity = sum_quantity;
    }
}
