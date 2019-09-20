package com.xframework.model;

import com.google.gson.Gson;

public abstract class BaseModel {

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this, this.getClass());
    }
}
