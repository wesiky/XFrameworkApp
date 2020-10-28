package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class LTDelivery extends BaseModel implements Serializable {
    private int id;
    private String barcode;
    private String batch_code;
    private int beltline_id;
    private String component_code;
    private String supplier_code;
    private float qty;
    private int delivery_type;
    private Date create_date;
    private String create_user;
    private Date last_update_date;
    private String last_update_user;
    private boolean enable = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        String[] str = barcode.split("\\|");
        if (str.length == 3) {
            this.barcode = barcode;
            this.batch_code = str[0];
            this.component_code = str[1];
            this.supplier_code = str[2];
        }
        else
        {
            this.barcode = "";
            this.batch_code = "";
            this.component_code = "";
            this.supplier_code = "";
        }
    }

    public String getBatchCode() {
        return batch_code;
    }

    public int getBeltlineId() {
        return beltline_id;
    }

    public void setBeltlineId(int beltline_id) {
        this.beltline_id = beltline_id;
    }

    public String getComponentCode() {
        return component_code;
    }

    public String getSupplierCode() {
        return supplier_code;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public int getDeliveryType() {
        return delivery_type;
    }

    public void setDeliveryType(int delivery_type) {
        this.delivery_type = delivery_type;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
