package com.xframework.model;

import java.io.Serializable;

public class PWProductBarcode extends BaseModel implements Serializable {
    private String allocation_code;

    private int allocation_id;

    private String barcode;

    private String description;

    private boolean enable;

    private int intermediate_warehouse_id;

    private String outer_barcode;

    private String product_code;

    private int product_id;

    private String product_name;

    private String region_code;

    private int region_id;

    private int source_id;

    public String getAllocationCode() {
        return this.allocation_code;
    }

    public int getAllocationId() {
        return this.allocation_id;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getEnable() {
        return this.enable;
    }

    public int getIntermediateWarehouseId() {
        return this.intermediate_warehouse_id;
    }

    public String getOuterBarcode() {
        return this.outer_barcode;
    }

    public String getProductCode() {
        return this.product_code;
    }

    public int getProductId() {
        return this.product_id;
    }

    public String getProductName() {
        return this.product_name;
    }

    public String getRegionCode() {
        return this.region_code;
    }

    public int getRegionId() {
        return this.region_id;
    }

    public int getSourceId() {
        return this.source_id;
    }

    public void setAllocationCode(String allocation_code) {
        this.allocation_code = allocation_code;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setIntermediateWarehouseId(int intermediate_warehouse_id) {
        this.intermediate_warehouse_id = intermediate_warehouse_id;
    }

    public void setOuterBarcode(String outer_barcode) {
        this.outer_barcode = outer_barcode;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public void setRegionCode(String region_code) {
        this.region_code = region_code;
    }

    public void setRegionId(int region_id) {
        this.region_id = region_id;
    }

    public void setSourceId(int source_id) {
        this.source_id = source_id;
    }
}
