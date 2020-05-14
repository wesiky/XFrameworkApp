package com.xframework.model.WS;

import com.xframework.model.SWReturnOrderHead;
import java.util.ArrayList;

public class GetSpareReturnOrdersOut extends BaseWSOut {
    private ArrayList<SWReturnOrderHead> orders = new ArrayList<SWReturnOrderHead>();

    public ArrayList<SWReturnOrderHead> getOrders() {
        return this.orders;
    }

    public void setOrders(ArrayList<SWReturnOrderHead> orders) {
        this.orders = orders;
    }
}
