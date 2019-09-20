package com.xframework.model;

import java.io.Serializable;

public class PWProductBarcode extends BaseModel implements Serializable {
    private String barcode;
    private String outer_barcode;
    private int product_id;
    private int allocation_id;
    private String description;
    private boolean enable;
    private String product_code;
    private String product_name;
    private int intermediate_warehouse_id;
    private int region_id;
    private String region_code;
    private String allocation_code;
    private int source_id;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getOuterBarcode() {
        return outer_barcode;
    }

    public void setOuterBarcode(String outer_barcode) {
        this.outer_barcode = outer_barcode;
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public int getAllocationId() {
        return allocation_id;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getProductCode() {
        return product_code;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public int getIntermediateWarehouseId() {
        return intermediate_warehouse_id;
    }

    public void setIntermediateWarehouseId(int intermediate_warehouse_id) {
        this.intermediate_warehouse_id = intermediate_warehouse_id;
    }

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

    public String getAllocationCode() {
        return allocation_code;
    }

    public void setAllocationCode(String allocation_code) {
        this.allocation_code = allocation_code;
    }

    public int getSourceId() {
        return source_id;
    }

    public void setSourceId(int source_id) {
        this.source_id = source_id;
    }
}
