package com.xframework.delegate;

import com.xframework.model.WS.SuspendedBatchOrderOut;

public interface BatchDeliveryDelegate {
    void DeliveryBatch(String batchNo,int startIndex);
    void SuspendedBatch(String batchNo, int startIndex);
}
