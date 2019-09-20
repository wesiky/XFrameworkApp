package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class BaseAllocation implements Serializable {
    private int allocation_id;
    private String allocation_code;
    private int floor;
    private int region_id;
    private int max_quantity;
    private String bind_product_ids;
    private String description;
    private Date create_date;
    private String create_user;
    private Date last_update_date;
    private String last_update_user;
    private Boolean enable = true;
    private int intermediate_warehouse_id;
    private String intermediate_warehouse_code;
    private String intermediate_warehouse_name;
    private int status;
    private String region_code;
    private int floor_count;

    public int getAllocationId() {
        return allocation_id;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public String getAllocationCode() {
        return allocation_code;
    }

    public void setAllocationCode(String allocation_code) {
        this.allocation_code = allocation_code;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRegionId() {
        return region_id;
    }

    public void setRegionId(int region_id) {
        this.region_id = region_id;
    }

    public int getMaxQuantity() {
        return max_quantity;
    }

    public void setMaxQuantity(int max_quantity) {
        this.max_quantity = max_quantity;
    }

    public String getBindProductIds() {
        return bind_product_ids;
    }

    public void setBindProductIds(String bind_product_ids) {
        this.bind_product_ids = bind_product_ids;
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

    public int getIntermediateWarehouseId() {
        return intermediate_warehouse_id;
    }

    public void setIntermediateWarehouseId(int intermediate_warehouse_id) {
        this.intermediate_warehouse_id = intermediate_warehouse_id;
    }

    public String getIntermediateWarehouseCode() {
        return intermediate_warehouse_code;
    }

    public void setIntermediateWarehouseCode(String intermediate_warehouse_code) {
        this.intermediate_warehouse_code = intermediate_warehouse_code;
    }

    public String getIntermediateWarehouseName() {
        return intermediate_warehouse_name;
    }

    public void setIntermediateWarehouseName(String intermediate_warehouse_name) {
        this.intermediate_warehouse_name = intermediate_warehouse_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRegionCode() {
        return region_code;
    }

    public void setRegionCode(String region_code) {
        this.region_code = region_code;
    }

    public int getFloorCount() {
        return floor_count;
    }

    public void setFloorCount(int floor_count) {
        this.floor_count = floor_count;
    }
}
