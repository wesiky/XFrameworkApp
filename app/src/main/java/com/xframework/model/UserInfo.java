package com.xframework.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String email;

    private String lgort;

    private String mrp;

    private String phone;

    private String real_name;

    private String role_name;

    private int superior_id = -1;

    private String user_name;

    private int user_type = -1;

    private int warehouse_id = -1;

    public String getEmail() {
        return this.email;
    }

    public String getLgort() {
        return this.lgort;
    }

    public String getMrp() {
        return this.mrp;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getRealName() {
        return this.real_name;
    }

    public String getRoleName() {
        return this.role_name;
    }

    public int getSuperiorId() {
        return this.superior_id;
    }

    public String getUserName() {
        return this.user_name;
    }

    public int getUserType() {
        return this.user_type;
    }

    public int getWarehouseId() {
        return this.warehouse_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRealName(String real_name) {
        this.real_name = real_name;
    }

    public void setRoleName(String role_name) {
        this.role_name = role_name;
    }

    public void setSuperiorId(int superior_id) {
        this.superior_id = superior_id;
    }

    public void setUseName(String user_name) {
        this.user_name = user_name;
    }

    public void setUserType(int user_type) {
        this.user_type = user_type;
    }

    public void setWarehouseId(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }
}
