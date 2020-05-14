package com.xframework.model;

import java.io.Serializable;

public class MrpSapOrder extends BaseModel implements Serializable {
    private String batch_no;

    private int count;

    private int id;

    private String product_body_code;

    private int product_body_id;

    private String product_body_name;

    private String product_code;

    private String product_name;

    private String remark;

    private int status;

    public String getBatchNo() {
        return this.batch_no;
    }

    public int getCount() {
        return this.count;
    }

    public int getId() {
        return this.id;
    }

    public String getProductBodyCode() {
        return this.product_body_code;
    }

    public int getProductBodyId() {
        return this.product_body_id;
    }

    public String getProductBodyName() {
        return this.product_body_name;
    }

    public String getProductCode() {
        return this.product_code;
    }

    public String getProductName() {
        return this.product_name;
    }

    public String getRemark() {
        return this.remark;
    }

    public int getStatus() {
        return this.status;
    }

    public void setBatchNo(String batch_no) {
        this.batch_no = batch_no;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductBodyCode(String product_body_code) {
        this.product_body_code = product_body_code;
    }

    public void setProductBodyId(int product_body_id) {
        this.product_body_id = product_body_id;
    }

    public void setProductBodyName(String product_body_name) {
        this.product_body_name = product_body_name;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
