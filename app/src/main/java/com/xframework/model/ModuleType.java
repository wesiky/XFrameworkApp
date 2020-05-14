package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class ModuleType implements Serializable {
    public ModuleType[] child_module_types;

    public Date create_date;

    public String create_user;

    public boolean enable;

    public String icon;

    public Date last_update_date;

    public String last_update_user;

    public int module_type_count;

    public int module_type_depth;

    public String module_type_description;

    public int module_type_id;

    public String module_type_name;

    public int module_type_order;

    public int module_type_superior_id;

    public Module[] modules;

    public ModuleType[] getChildModuleTypes() {
        return this.child_module_types;
    }

    public Date getCreateDate() {
        return this.create_date;
    }

    public String getCreateUser() {
        return this.create_user;
    }

    public boolean getEnable() {
        return this.enable;
    }

    public String getIcon() {
        return this.icon;
    }

    public Date getLastUpdateDate() {
        return this.last_update_date;
    }

    public String getLastUpdateUser() {
        return this.last_update_user;
    }

    public int getModuleTypeCount() {
        return this.module_type_count;
    }

    public int getModuleTypeDepth() {
        return this.module_type_depth;
    }

    public String getModuleTypeDescription() {
        return this.module_type_description;
    }

    public int getModuleTypeId() {
        return this.module_type_id;
    }

    public String getModuleTypeName() {
        return this.module_type_name;
    }

    public int getModuleTypeOrder() {
        return this.module_type_order;
    }

    public int getModuleTypeSuperiorId() {
        return this.module_type_superior_id;
    }

    public Module[] getModules() {
        return this.modules;
    }

    public void setChildModuleTypes(ModuleType[] child_module_types) {
        this.child_module_types = child_module_types;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }

    public void setCreateUser(String create_user) {
        this.create_user = create_user;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public void setModuleTypeCount(int module_type_count) {
        this.module_type_count = module_type_count;
    }

    public void setModuleTypeDepth(int module_type_depth) {
        this.module_type_depth = module_type_depth;
    }

    public void setModuleTypeDescription(String module_type_description) {
        this.module_type_description = module_type_description;
    }

    public void setModuleTypeId(int module_type_id) {
        this.module_type_id = module_type_id;
    }

    public void setModuleTypeName(String module_type_name) {
        this.module_type_name = module_type_name;
    }

    public void setModuleTypeOrder(int module_type_order) {
        this.module_type_order = module_type_order;
    }

    public void setModuleTypeSuperiorId(int module_type_superior_id) {
        this.module_type_superior_id = module_type_superior_id;
    }

    public void setModules(Module[] modules) {
        this.modules = modules;
    }
}
