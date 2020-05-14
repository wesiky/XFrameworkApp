package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class PWProductMoveOrderHeader extends BaseModel implements Serializable {
    private Date create_date;

    private String create_user;

    private String description;

    private boolean enable = true;

    private String in_allocation_code;

    private int in_allocation_id;

    private String intermediate_warehouse_code;

    private int intermediate_warehouse_id;

    private String intermediate_warehouse_name;

    private Date last_update_date;

    private String last_update_user;

    private String order_code;

    private String out_allocation_code;

    private int out_allocation_id;

    private int status = 0;

    public Date getCreateDate() {
        return this.create_date;
    }

    public String getCreateUser() {
        return this.create_user;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getEnable() {
        return this.enable;
    }

    public String getInAllocationCode() {
        return this.in_allocation_code;
    }

    public int getInAllocationId() {
        return this.in_allocation_id;
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

    public String getOrderCode() {
        return this.order_code;
    }

    public String getOutAllocationCode() {
        return this.out_allocation_code;
    }

    public int getOutAllocationId() {
        return this.out_allocation_id;
    }

    public int getStatus() {
        return this.status;
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

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setInAllocationCode(String in_allocation_code) {
        this.in_allocation_code = in_allocation_code;
    }

    public void setInAllocationId(int in_allocation_id) {
        this.in_allocation_id = in_allocation_id;
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

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }

    public void setOutAllocationCode(String out_allocation_code) {
        this.out_allocation_code = out_allocation_code;
    }

    public void setOutAllocationId(int out_allocation_id) {
        this.out_allocation_id = out_allocation_id;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
