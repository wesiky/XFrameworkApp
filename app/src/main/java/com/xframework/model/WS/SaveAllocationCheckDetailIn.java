package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWCheckOrderDetailAllocation;
import com.xframework.model.PWCheckOrderHeader;
import com.xframework.model.PWDeliveryOrderDetail;

import java.util.ArrayList;
import java.util.List;

public class SaveAllocationCheckDetailIn extends BaseModel {
    private int user_id;
    private int option_type;
    private int allocation_id;
    private String order_code;
    private String device_code;
    private List<PWCheckOrderDetailAllocation> body = new ArrayList<>();

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getOptionType() {
        return option_type;
    }

    public void setOptionType(int option_type) {
        this.option_type = option_type;
    }

    public int getAllocationId() {
        return allocation_id;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public String getDeviceCode() {
        return device_code;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public String getOrderCode() {
        return order_code;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }

    public List<PWCheckOrderDetailAllocation> getBody() {
        return body;
    }

    public void setBody(List<PWCheckOrderDetailAllocation> body) {
        this.body = body;
    }
}
