package com.xframework.model;

import com.google.gson.Gson;

public abstract class BaseModel {
    public String toJson() {
        return (new Gson()).toJson(this, getClass());
    }
}
