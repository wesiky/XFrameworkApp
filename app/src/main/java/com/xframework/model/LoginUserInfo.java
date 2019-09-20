package com.xframework.model;

import java.io.Serializable;

public class LoginUserInfo implements Serializable {
    private static int user_id=-1;
    private static String user_name;
    private static String email;
    private static String real_name;
    private static String phone;
    private static int warehouse_id;
    private static String lgort;
    private static String mrp;
    private static int superior_id;
    private static String role_name;

    public static int getUserId() {
        return user_id;
    }

    public static void setUserId(int user_id) {
        LoginUserInfo.user_id = user_id;
    }

    public static String getUserName() {
        return user_name;
    }

    public static void setUserName(String user_name) {
        LoginUserInfo.user_name = user_name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        LoginUserInfo.email = email;
    }

    public static String getRealName() {
        return real_name;
    }

    public static void setRealName(String real_name) {
        LoginUserInfo.real_name = real_name;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        LoginUserInfo.phone = phone;
    }

    public static int getWarehouseId() {
        return warehouse_id;
    }

    public static void setWarehouseId(int warehouse_id) {
        LoginUserInfo.warehouse_id = warehouse_id;
    }

    public static String getLgort() {
        return lgort;
    }

    public static void setLgort(String lgort) {
        LoginUserInfo.lgort = lgort;
    }

    public static String getMrp() {
        return mrp;
    }

    public static void setMrp(String mrp) {
        LoginUserInfo.mrp = mrp;
    }

    public static int getSuperiorId() {
        return superior_id;
    }

    public static void setSuperiorId(int superior_id) {
        LoginUserInfo.superior_id = superior_id;
    }

    public static String getRoleName() {
        return role_name;
    }

    public static void setRoleName(String role_name) {
        LoginUserInfo.role_name = role_name;
    }

    public static void clear(){
        LoginUserInfo.user_id = -1;
        LoginUserInfo.user_name = "";
        LoginUserInfo.email = "";
        LoginUserInfo.real_name = "";
        LoginUserInfo.phone = "";
        LoginUserInfo.warehouse_id = -1;
        LoginUserInfo.lgort = "";
        LoginUserInfo.mrp = "";
        LoginUserInfo.superior_id = -1;
        LoginUserInfo.role_name = "";
    }
}
