package com.xframework.model;

import java.io.Serializable;

public class LoginUserInfo implements Serializable {
    private static String email;

    private static String lgort;

    private static String mrp;

    private static String phone;

    private static String real_name;

    private static String role_name;

    private static int superior_id;

    private static int user_id = -1;

    private static String user_name;

    private static int warehouse_id;

    public static void clear() {
        user_id = -1;
        user_name = "";
        email = "";
        real_name = "";
        phone = "";
        warehouse_id = -1;
        lgort = "";
        mrp = "";
        superior_id = -1;
        role_name = "";
    }

    public static String getEmail() {
        return email;
    }

    public static String getLgort() {
        return lgort;
    }

    public static String getMrp() {
        return mrp;
    }

    public static String getPhone() {
        return phone;
    }

    public static String getRealName() {
        return real_name;
    }

    public static String getRoleName() {
        return role_name;
    }

    public static int getSuperiorId() {
        return superior_id;
    }

    public static int getUserId() {
        return user_id;
    }

    public static String getUserName() {
        return user_name;
    }

    public static int getWarehouseId() {
        return warehouse_id;
    }

    public static void setEmail(String email) {
        LoginUserInfo.email = email;
    }

    public static void setLgort(String lgort) {
        LoginUserInfo.lgort = lgort;
    }

    public static void setMrp(String mrp) {
        LoginUserInfo.mrp = mrp;
    }

    public static void setPhone(String phone) {
        LoginUserInfo.phone = phone;
    }

    public static void setRealName(String real_name) {
        LoginUserInfo.real_name = real_name;
    }

    public static void setRoleName(String role_name) {
        LoginUserInfo.role_name = role_name;
    }

    public static void setSuperiorId(int superior_id) {
        LoginUserInfo.superior_id = superior_id;
    }

    public static void setUserId(int user_id) {
        LoginUserInfo.user_id = user_id;
    }

    public static void setUserName(String user_name) {
        LoginUserInfo.user_name = user_name;
    }

    public static void setWarehouseId(int warehouse_id) {
        LoginUserInfo.warehouse_id = warehouse_id;
    }
}
