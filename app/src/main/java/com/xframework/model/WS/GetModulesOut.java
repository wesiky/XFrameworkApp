package com.xframework.model.WS;

import com.xframework.model.ModuleType;

public class GetModulesOut {
    private int status;
    private String msg;
    private ModuleType[] module_types;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ModuleType[] getModuleTypes() {
        return module_types;
    }

    public void setModuleTypes(ModuleType[] module_types) {
        this.module_types = module_types;
    }
}
