package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class Module implements Serializable {
    private String authority_list;

    private Date create_date;

    private String create_user;

    private boolean enable;

    private int framework_id;

    private String icon;

    private boolean is_menu;

    private Date last_update_date;

    private String last_update_user;

    private String module_description;

    private boolean module_disabled;

    private int module_id;

    private String module_name;

    private int module_order;

    private String module_tag;

    private int module_type_id;

    private String module_url;

    private int show_type;

    public String getAuthorityList() {
        return this.authority_list;
    }

    public Date getCreateDate() {
        return this.create_date;
    }

    public String getCreateUser() {
        return this.create_user;
    }

    public int getFrameworkId() {
        return this.framework_id;
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

    public String getModuleDescription() {
        return this.module_description;
    }

    public int getModuleId() {
        return this.module_id;
    }

    public String getModuleName() {
        return this.module_name;
    }

    public int getModuleOrder() {
        return this.module_order;
    }

    public String getModuleTag() {
        return this.module_tag;
    }

    public int getModuleTypeId() {
        return this.module_type_id;
    }

    public String getModuleUrl() {
        return this.module_url;
    }

    public int getShowType() {
        return this.show_type;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public boolean isIsMenu() {
        return this.is_menu;
    }

    public boolean isModuleDisabled() {
        return this.module_disabled;
    }

    public void setAuthorityList(String authority_list) {
        this.authority_list = authority_list;
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

    public void setFrameworkId(int framework_id) {
        this.framework_id = framework_id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setIsMenu(boolean is_menu) {
        this.is_menu = is_menu;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public void setModuleDescription(String module_description) {
        this.module_description = module_description;
    }

    public void setModuleDisabled(boolean module_disabled) {
        this.module_disabled = module_disabled;
    }

    public void setModuleId(int module_id) {
        this.module_id = module_id;
    }

    public void setModuleName(String module_name) {
        this.module_name = module_name;
    }

    public void setModuleOrder(int module_order) {
        this.module_order = module_order;
    }

    public void setModuleTag(String module_tag) {
        this.module_tag = module_tag;
    }

    public void setModuleTypeId(int module_type_id) {
        this.module_type_id = module_type_id;
    }

    public void setModuleUrl(String module_url) {
        this.module_url = module_url;
    }

    public void setShowType(int show_type) {
        this.show_type = show_type;
    }
}
