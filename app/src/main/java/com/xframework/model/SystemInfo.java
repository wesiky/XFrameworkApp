package com.xframework.model;

import java.io.Serializable;

public class SystemInfo implements Serializable {
    private static String device_code;

    private static int framework_id = 1;

    static {
        device_code = "";
    }

    public static String getDeviceCode() {
        return device_code;
    }

    public static int getFrameworkId() {
        return framework_id;
    }

    public static void setDeviceCode(String device_code) {
        SystemInfo.device_code = device_code;
    }

    public static void setFrameworkId(int framework_id) {
        SystemInfo.framework_id = framework_id;
    }
}
