package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.MrpSapOrder;
import com.xframework.model.PWCheckOrderDetailAllocation;
import com.xframework.model.PWReturnOrderDetail;
import com.xframework.model.PWReturnOrderHeader;

import java.util.ArrayList;
import java.util.List;

public class SuspendedBatchOrderIn extends BaseModel {
    private int user_id;
    private String device_code;
    private List<MrpSapOrder> product_list = new ArrayList<>();
    private String batch_no;


    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getDeviceCode() {
        return device_code;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public List<MrpSapOrder> getProductList() {
        return product_list;
    }

    public void setProductList(List<MrpSapOrder> product_list) {
        this.product_list = product_list;
    }

    public String getBatchNo() {
        return batch_no;
    }

    public void setBatchNo(String batch_no) {
        this.batch_no = batch_no;
    }
}
