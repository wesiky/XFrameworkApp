package com.xframework.model;

import java.io.Serializable;
import java.util.Date;

public class BaseProduct extends BaseModel implements Serializable {
    private int product_id;
    private String product_code;
    private String product_name;
    private String product_description;
    private String unit;
    private String product_model;
    private String product_series;
    private String product_category;
    private int pcs;
    private String product_group;
    private float sale_price;
    private int install_fee_type;
    private Date create_date;
    private String create_user;
    private Date last_update_date;
    private String last_update_user;
    private Boolean enable= true;
    private Boolean is_intermediate_warehouse_product = false;

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
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

    public String getProductDescription() {
        return product_description;
    }

    public void setProductDescription(String product_description) {
        this.product_description = product_description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductModel() {
        return product_model;
    }

    public void setProductModel(String product_model) {
        this.product_model = product_model;
    }

    public String getProductSeries() {
        return product_series;
    }

    public void setProductSeries(String product_series) {
        this.product_series = product_series;
    }

    public String getProductCategory() {
        return product_category;
    }

    public void setProductCategory(String product_category) {
        this.product_category = product_category;
    }

    public int getPcs() {
        return pcs;
    }

    public void setPcs(int pcs) {
        this.pcs = pcs;
    }

    public String getProductGroup() {
        return product_group;
    }

    public void setProductGroup(String product_group) {
        this.product_group = product_group;
    }

    public float getSalePrice() {
        return sale_price;
    }

    public void setSalePrice(float sale_price) {
        this.sale_price = sale_price;
    }

    public int getInstallFeeType() {
        return install_fee_type;
    }

    public void setInstallFeeType(int install_fee_type) {
        this.install_fee_type = install_fee_type;
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

    public Boolean getIsIntermediateWarehouseProduct() {
        return is_intermediate_warehouse_product;
    }

    public void setIsIntermediateWarehouseProduct(Boolean is_intermediate_warehouse_product) {
        this.is_intermediate_warehouse_product = is_intermediate_warehouse_product;
    }
}
