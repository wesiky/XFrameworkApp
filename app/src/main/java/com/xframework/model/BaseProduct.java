package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class BaseProduct extends BaseModel implements Serializable {
    private Date create_date;

    private String create_user;

    private Boolean enable = Boolean.valueOf(true);

    private int install_fee_type;

    private Boolean is_intermediate_warehouse_product = Boolean.valueOf(false);

    private Date last_update_date;

    private String last_update_user;

    private int pcs;

    private String product_category;

    private String product_code;

    private String product_description;

    private String product_group;

    private int product_id;

    private String product_model;

    private String product_name;

    private String product_series;

    private float sale_price;

    private String unit;

    public Date getCreateDate() {
        return this.create_date;
    }

    public String getCreateUser() {
        return this.create_user;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public int getInstallFeeType() {
        return this.install_fee_type;
    }

    public Boolean getIsIntermediateWarehouseProduct() {
        return this.is_intermediate_warehouse_product;
    }

    public Date getLastUpdateDate() {
        return this.last_update_date;
    }

    public String getLastUpdateUser() {
        return this.last_update_user;
    }

    public int getPcs() {
        return this.pcs;
    }

    public String getProductCategory() {
        return this.product_category;
    }

    public String getProductCode() {
        return this.product_code;
    }

    public String getProductDescription() {
        return this.product_description;
    }

    public String getProductGroup() {
        return this.product_group;
    }

    public int getProductId() {
        return this.product_id;
    }

    public String getProductModel() {
        return this.product_model;
    }

    public String getProductName() {
        return this.product_name;
    }

    public String getProductSeries() {
        return this.product_series;
    }

    public float getSalePrice() {
        return this.sale_price;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }

    public void setCreateUser(String create_user) {
        this.create_user = create_user;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setInstallFeeType(int install_fee_type) {
        this.install_fee_type = install_fee_type;
    }

    public void setIsIntermediateWarehouseProduct(Boolean is_intermediate_warehouse_product) {
        this.is_intermediate_warehouse_product = is_intermediate_warehouse_product;
    }

    public void setLastUpdateDate(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public void setLastUpdateUser(String last_update_user) {
        this.last_update_user = last_update_user;
    }

    public void setPcs(int pcs) {
        this.pcs = pcs;
    }

    public void setProductCategory(String product_category) {
        this.product_category = product_category;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public void setProductDescription(String product_description) {
        this.product_description = product_description;
    }

    public void setProductGroup(String product_group) {
        this.product_group = product_group;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public void setProductModel(String product_model) {
        this.product_model = product_model;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public void setProductSeries(String product_series) {
        this.product_series = product_series;
    }

    public void setSalePrice(float sale_price) {
        this.sale_price = sale_price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
