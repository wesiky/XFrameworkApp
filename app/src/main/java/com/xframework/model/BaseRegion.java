package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class BaseRegion implements Serializable {
    private int region_id;
    private String region_code;
    private int intermediate_warehouse_id;
    private int floor_count;
    private String description;
    private Date create_date;
    private String create_user;
    private Date last_update_date;
    private String last_update_user;
    private Boolean enable = true;

    public int getRegionId() {
        return region_id;
    }

    public void setRegionId(int region_id) {
        this.region_id = region_id;
    }

    public String getRegionCode() {
        return region_code;
    }

    public void setRegionCode(String region_code) {
        this.region_code = region_code;
    }

    public int getIntermediateWarehouseId() {
        return intermediate_warehouse_id;
    }

    public void setIntermediateWarehouseId(int intermediate_warehouse_id) {
        this.intermediate_warehouse_id = intermediate_warehouse_id;
    }

    public int getFloorCount() {
        return floor_count;
    }

    public void setFloorCount(int floor_count) {
        this.floor_count = floor_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
