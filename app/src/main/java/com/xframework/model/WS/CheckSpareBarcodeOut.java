package com.xframework.model.WS;

import com.xframework.model.SWSpareBarcode;

public class CheckSpareBarcodeOut extends BaseWSOut {
    private SWSpareBarcode barcode;

    public SWSpareBarcode getBarcode() {
        return this.barcode;
    }

    public void setBarcode(SWSpareBarcode barcode) {
        this.barcode = barcode;
    }
}
