package com.xframework.delegate;

import com.xframework.model.BaseAllocation;

public interface AllocationDelegate {
    boolean recheck(String orderCode, BaseAllocation allocation);
}
