package com.xframework.model;

import java.util.Date;

public class BaseOrderBody extends BaseModel {
    private Date create_date;

    private String create_user;

    private String description;

    private boolean enable = true;

    private int id;

    private Date last_update_date;

    private String last_update_user;

    private String order_code;

    private float quantity;

    private String spare_code;

    private int spare_id;

    private String spare_model;

    private String spare_name;

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
}
