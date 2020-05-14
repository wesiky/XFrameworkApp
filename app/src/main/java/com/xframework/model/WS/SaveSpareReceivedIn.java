package com.xframework.model.WS;

import com.xframework.model.BaseOrderBarcode;
import com.xframework.model.SWReceivedOrderHead;
import java.util.ArrayList;
import java.util.List;

public class SaveSpareReceivedIn extends BaseWSIn {
    private List<BaseOrderBarcode> barcodes = new ArrayList<BaseOrderBarcode>();

    private SWReceivedOrderHead head = new SWReceivedOrderHead();

    public List<BaseOrderBarcode> getBarcodes() {
        return this.barcodes;
    }

    public SWReceivedOrderHead getHead() {
        return this.head;
    }

    public void setBarcodes(List<BaseOrderBarcode> barcodes) {
        this.barcodes = barcodes;
    }

    public void setHead(SWReceivedOrderHead head) {
        this.head = head;
    }
}
