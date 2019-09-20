package com.xframework.model;

import java.io.Serializable;

public class MrpSapOrder extends BaseModel implements Serializable {
    private int id;
    private String batch_no;
    private String product_name;
    private String product_code;
    private int product_body_id;
    private String product_body_name;
    private String product_body_code;
    private int status;
    private int count;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batch_no;
    }

    public void setBatchNo(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public String getProductCode() {
        return product_code;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public int getProductBodyId() {
        return product_body_id;
    }

    public void setProductBodyId(int product_body_id) {
        this.product_body_id = product_body_id;
    }

    public String getProductBodyName() {
        return product_body_name;
    }

    public void setProductBodyName(String product_name) {
        this.product_body_name = product_name;
    }

    public String getProductBodyCode() {
        return product_body_code;
    }

    public void setProductBodyCode(String product_code) {
        this.product_body_code = product_code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
