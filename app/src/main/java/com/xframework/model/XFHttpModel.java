package com.xframework.model;

import java.io.Serializable;
import java.util.HashMap;

public class XFHttpModel extends BaseModel implements Serializable {
    private String methodName = "";

    private HashMap<String, String> params = new HashMap<>();

    public XFHttpModel() {}

    public XFHttpModel(String methodName, HashMap<String, String> params) {
        this.methodName = methodName;
        this.params = params;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public HashMap<String, String> getParams() {
        return this.params;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
