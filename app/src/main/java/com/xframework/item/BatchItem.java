package com.xframework.item;

import java.io.Serializable;

public class BatchItem  implements Serializable {

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    private String batchNo;
}
