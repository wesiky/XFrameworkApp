package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class PWProductMoveOrderHeader extends BaseModel implements Serializable {
    private String order_code;
    private int intermediate_warehouse_id;
    private String intermediate_warehouse_code;
    private String intermediate_warehouse_name;
    private int out_allocation_id;
    private String out_allocation_code;
    private int in_allocation_id;
    private String in_allocation_code;
    private int status = 0;
    private String description;
    private Date create_date;
    private String create_user;
    private Date last_update_date;
    private String last_update_user;
    private boolean enable = true;

    public String getOrderCode() {
        return order_code;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
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

    public int getOutAllocationId() {
        return out_allocation_id;
    }

    public void setOutAllocationId(int out_allocation_id) {
        this.out_allocation_id = out_allocation_id;
    }

    public String getOutAllocationCode() {
        return out_allocation_code;
    }

    public void setOutAllocationCode(String out_allocation_code) {
        this.out_allocation_code = out_allocation_code;
    }

    public int getInAllocationId() {
        return in_allocation_id;
    }

    public void setInAllocationId(int in_allocation_id) {
        this.in_allocation_id = in_allocation_id;
    }

    public String getInAllocationCode() {
        return in_allocation_code;
    }

    public void setInAllocationCode(String in_allocation_code) {
        this.in_allocation_code = in_allocation_code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
