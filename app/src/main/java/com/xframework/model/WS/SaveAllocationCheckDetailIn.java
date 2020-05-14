package com.xframework.model.WS;

import com.xframework.model.BaseModel;
import com.xframework.model.PWCheckOrderDetailAllocation;
import java.util.ArrayList;
import java.util.List;

public class SaveAllocationCheckDetailIn extends BaseModel {
    private int allocation_id;

    private List<PWCheckOrderDetailAllocation> body = new ArrayList<PWCheckOrderDetailAllocation>();

    private String device_code;

    private int option_type;

    private String order_code;

    private int user_id;

    public int getAllocationId() {
        return this.allocation_id;
    }

    public List<PWCheckOrderDetailAllocation> getBody() {
        return this.body;
    }

    public String getDeviceCode() {
        return this.device_code;
    }

    public int getOptionType() {
        return this.option_type;
    }

    public String getOrderCode() {
        return this.order_code;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setAllocationId(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public void setBody(List<PWCheckOrderDetailAllocation> body) {
        this.body = body;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public void setOptionType(int option_type) {
        this.option_type = option_type;
    }

    public void setOrderCode(String order_code) {
        this.order_code = order_code;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
