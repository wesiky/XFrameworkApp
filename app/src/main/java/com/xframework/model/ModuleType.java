package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class ModuleType implements Serializable {
    public Module[] modules;
    public ModuleType[] child_module_types;
    public String icon;
    public int module_type_id;
    public String module_type_name;
    public int module_type_order;
    public String module_type_description;
    public int module_type_depth;
    public int module_type_superior_id;
    public int module_type_count;
    public Date create_date;
    public String create_user;
    public Date last_update_date;
    public String last_update_user;
    public boolean enable;

    public Module[] getModules() {
        return modules;
    }

    public void setModules(Module[] modules) {
        this.modules = modules;
    }

    public ModuleType[] getChildModuleTypes() {
        return child_module_types;
    }

    public void setChildModuleTypes(ModuleType[] child_module_types) {
        this.child_module_types = child_module_types;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getModuleTypeId() {
        return module_type_id;
    }

    public void setModuleTypeId(int module_type_id) {
        this.module_type_id = module_type_id;
    }

    public String getModuleTypeName() {
        return module_type_name;
    }

    public void setModuleTypeName(String module_type_name) {
        this.module_type_name = module_type_name;
    }

    public int getModuleTypeOrder() {
        return module_type_order;
    }

    public void setModuleTypeOrder(int module_type_order) {
        this.module_type_order = module_type_order;
    }

    public String getModuleTypeDescription() {
        return module_type_description;
    }

    public void setModuleTypeDescription(String module_type_description) {
        this.module_type_description = module_type_description;
    }

    public int getModuleTypeDepth() {
        return module_type_depth;
    }

    public void setModuleTypeDepth(int module_type_depth) {
        this.module_type_depth = module_type_depth;
    }

    public int getModuleTypeSuperiorId() {
        return module_type_superior_id;
    }

    public void setModuleTypeSuperiorId(int module_type_superior_id) {
        this.module_type_superior_id = module_type_superior_id;
    }

    public int getModuleTypeCount() {
        return module_type_count;
    }

    public void setModuleTypeCount(int module_type_count) {
        this.module_type_count = module_type_count;
    }

    public Date getCreateDate() {
        return create_date;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreateUser() {
        return create_user;
    }

    public void setCreateUser(String create_user) {
        this.create_user = create_user;
    }

    public Date getLastUpdateDate() {
        return last_update_date;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public String getLastUpdateUser() {
        return last_update_user;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
