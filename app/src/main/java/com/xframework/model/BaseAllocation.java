package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class BaseAllocation implements Serializable {
    private String allocation_code;

    private int allocation_id;

    private String bind_product_ids;

    private Date create_date;

    private String create_user;

    private String description;

    private Boolean enable = Boolean.valueOf(true);

    private int floor;

    private int floor_count;

    private String intermediate_warehouse_code;

    private int intermediate_warehouse_id;

    private String intermediate_warehouse_name;

    private Date last_update_date;

    private String last_update_user;

    private int max_quantity;

    private String region_code;

    private int region_id;

    private int status;

    public String getAllocationCode() {
        return this.allocation_code;
    }

    public int getAllocationId() {
        return this.allocation_id;
    }

    public String getBindProductIds() {
        return this.bind_product_ids;
    }

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

    public int getFloor() {
        return this.floor;
    }

    public int getFloorCount() {
        return this.floor_count;
    }

    public String getIntermediateWarehouseCode() {
        return this.intermediate_warehouse_code;
    }

    public int getIntermediateWarehouseId() {
        return this.intermediate_warehouse_id;
    }

    public String getIntermediateWarehouseName() {
        return this.intermediate_warehouse_name;
    }

    public Date getLastUpdateDate() {
        return this.last_update_date;
    }

    public String getLastUpdateUser() {
        return this.last_update_user;
    }

    public int getMaxQuantity() {
        return this.max_quantity;
    }

    public String getRegionCode() {
        return this.region_code;
    }

    public int getRegionId() {
        return this.region_id;
    }

    public int getStatus() {
        return this.status;
    }

    public void setAllocationCode(String allocation_code) {
        this.allocation_code = allocation_code;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public void setBindProductIds(String bind_product_ids) {
        this.bind_product_ids = bind_product_ids;
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

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setFloorCount(int floor_count) {
        this.floor_count = floor_count;
    }

    public void setIntermediateWarehouseCode(String intermediate_warehouse_code) {
        this.intermediate_warehouse_code = intermediate_warehouse_code;
    }

    public void setIntermediateWarehouseId(int intermediate_warehouse_id) {
        this.intermediate_warehouse_id = intermediate_warehouse_id;
    }

    public void setIntermediateWarehouseName(String intermediate_warehouse_name) {
        this.intermediate_warehouse_name = intermediate_warehouse_name;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public void setMaxQuantity(int max_quantity) {
        this.max_quantity = max_quantity;
    }

    public void setRegionCode(String region_code) {
        this.region_code = region_code;
    }

    public void setRegionId(int region_id) {
        this.region_id = region_id;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
