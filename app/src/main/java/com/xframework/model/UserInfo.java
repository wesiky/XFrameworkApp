package com.xframework.model;

import java.io.Serializable;

public class UserInfo  implements Serializable {
    private String user_name;
    private String email;
    private String real_name;
    private String phone;
    private int warehouse_id = -1;
    private String lgort;
    private String mrp;
    private int superior_id = -1;
    private String role_name;
    private int user_type = -1;

    public String getUserName() {
        return user_name;
    }

    public void setUseName(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return real_name;
    }

    public void setRealName(String real_name) {
        this.real_name = real_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getWarehouseId() {
        return warehouse_id;
    }

    public void setWarehouseId(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getLgort() {
        return lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public int getSuperiorId() {
        return superior_id;
    }

    public void setSuperiorId(int superior_id) {
        this.superior_id = superior_id;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleName(String role_name) {
        this.role_name = role_name;
    }

    public int getUserType() {
        return user_type;
    }

    public void setUserType(int user_type) {
        this.user_type = user_type;
    }
}
