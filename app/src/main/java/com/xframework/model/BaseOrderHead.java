package com.xframework.model;

import java.util.Date;

public class BaseOrderHead extends BaseModel {
    private Date create_date;

    private String create_user;

    private String description;

    private boolean enable = true;

    private Date last_update_date;

    private String last_update_user;

    private String order_code;

    private int status;

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

    public String getOrderCode() {
        return this.order_code;
    }

    public int getStatus() {
        return this.status;
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

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
