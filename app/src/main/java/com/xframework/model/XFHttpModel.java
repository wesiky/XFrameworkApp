package com.xframework.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;

public class XFHttpModel  extends BaseModel implements Serializable {
    private String methodName = "";
    private HashMap<String,String> params = new HashMap<>();

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public XFHttpModel() {
    }

    public XFHttpModel(String methodName,HashMap<String,String> params)
    {
        this.methodName = methodName;
        this.params = params;
    }
}
