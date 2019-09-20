package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class Module implements Serializable {
    private String authority_list;
    private String icon;
    private int module_id;
    private int module_type_id;
    private String module_name;
    private String module_tag;
    private String module_url;
    private boolean module_disabled;
    private int module_order;
    private String module_description;
    private boolean is_menu;
    private int show_type;
    private Date create_date;
    private String create_user;
    private Date last_update_date;
    private String last_update_user;
    private boolean enable;
    private int framework_id;

    public String getAuthorityList() {
        return authority_list;
    }

    public void setAuthorityList(String authority_list) {
        this.authority_list = authority_list;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getModuleId() {
        return module_id;
    }

    public void setModuleId(int module_id) {
        this.module_id = module_id;
    }

    public int getModuleTypeId() {
        return module_type_id;
    }

    public void setModuleTypeId(int module_type_id) {
        this.module_type_id = module_type_id;
    }

    public String getModuleName() {
        return module_name;
    }

    public void setModuleName(String module_name) {
        this.module_name = module_name;
    }

    public String getModuleTag() {
        return module_tag;
    }

    public void setModuleTag(String module_tag) {
        this.module_tag = module_tag;
    }

    public String getModuleUrl() {
        return module_url;
    }

    public void setModuleUrl(String module_url) {
        this.module_url = module_url;
    }

    public boolean isModuleDisabled() {
        return module_disabled;
    }

    public void setModuleDisabled(boolean module_disabled) {
        this.module_disabled = module_disabled;
    }

    public int getModuleOrder() {
        return module_order;
    }

    public void setModuleOrder(int module_order) {
        this.module_order = module_order;
    }

    public String getModuleDescription() {
        return module_description;
    }

    public void setModuleDescription(String module_description) {
        this.module_description = module_description;
    }

    public boolean isIsMenu() {
        return is_menu;
    }

    public void setIsMenu(boolean is_menu) {
        this.is_menu = is_menu;
    }

    public int getShowType() {
        return show_type;
    }

    public void setShowType(int show_type) {
        this.show_type = show_type;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getFrameworkId() {
        return framework_id;
    }

    public void setFrameworkId(int framework_id) {
        this.framework_id = framework_id;
    }
}
