package com.xframework.model.WS;

import com.xframework.model.BaseOrderBarcode;
import com.xframework.model.SWReturnOrderHead;
import java.util.ArrayList;
import java.util.List;

public class SaveSpareReturnIn extends BaseWSIn {
    private List<BaseOrderBarcode> barcodes = new ArrayList<BaseOrderBarcode>();

    private SWReturnOrderHead head;

    public List<BaseOrderBarcode> getBarcodes() {
        return this.barcodes;
    }

    public SWReturnOrderHead getHead() {
        return this.head;
    }

    public void setBarcodes(List<BaseOrderBarcode> barcodes) {
        this.barcodes = barcodes;
    }

    public void setHead(SWReturnOrderHead head) {
        this.head = head;
    }
}
