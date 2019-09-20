package com.xframework.model.WS;

import com.xframework.model.BaseModel;

public class CheckDeliveryBarcodeIn extends BaseModel {
    private int user_id;
    private int warehouse_id;
    private String device_code;
    private String barcode;

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getWarehouseId() {
        return warehouse_id;
    }

    public void setWarehouseId(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getDeviceCode() {
        return device_code;
    }

    public void setDeviceCode(String device_code) {
        this.device_code = device_code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
