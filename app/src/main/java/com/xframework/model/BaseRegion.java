package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class BaseRegion implements Serializable {
    private Date create_date;

    private String create_user;

    private String description;

    private Boolean enable = Boolean.valueOf(true);

    private int floor_count;

    private int intermediate_warehouse_id;

    private Date last_update_date;

    private String last_update_user;

    private String region_code;

    private int region_id;

    public Date getCreateDate() {
        return this.create_date;
    }

    public String getCreateUser() {
        return this.create_user;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public int getFloorCount() {
        return this.floor_count;
    }

    public int getIntermediateWarehouseId() {
        return this.intermediate_warehouse_id;
    }

    public Date getLastUpdateDate() {
        return this.last_update_date;
    }

    public String getLastUpdateUser() {
        return this.last_update_user;
    }

    public String getRegionCode() {
        return this.region_code;
    }

    public int getRegionId() {
        return this.region_id;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }

    public void setCreateUser(String create_user) {
        this.create_user = create_user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setFloorCount(int floor_count) {
        this.floor_count = floor_count;
    }

    public void setIntermediateWarehouseId(int intermediate_warehouse_id) {
        this.intermediate_warehouse_id = intermediate_warehouse_id;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public void setRegionCode(String region_code) {
        this.region_code = region_code;
    }

    public void setRegionId(int region_id) {
        this.region_id = region_id;
    }
}
