package com.xframework.model.WS;

import com.xframework.model.BaseOrderBarcode;
import com.xframework.model.SWRequestOrderHead;
import java.util.ArrayList;
import java.util.List;

public class SaveSpareRequestIn extends BaseWSIn {
    private List<BaseOrderBarcode> barcodes = new ArrayList<BaseOrderBarcode>();

    private SWRequestOrderHead head;

    public List<BaseOrderBarcode> getBarcodes() {
        return this.barcodes;
    }

    public SWRequestOrderHead getHead() {
        return this.head;
    }

    public void setBarcodes(List<BaseOrderBarcode> barcodes) {
        this.barcodes = barcodes;
    }

    public void setHead(SWRequestOrderHead head) {
        this.head = head;
    }
}
