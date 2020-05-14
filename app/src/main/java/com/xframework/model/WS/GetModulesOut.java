package com.xframework.model.WS;

import com.xframework.model.ModuleType;

public class GetModulesOut {
    private ModuleType[] module_types;

    private String msg;

    private int status;

    public ModuleType[] getModuleTypes() {
        return this.module_types;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setModuleTypes(ModuleType[] module_types) {
        this.module_types = module_types;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
