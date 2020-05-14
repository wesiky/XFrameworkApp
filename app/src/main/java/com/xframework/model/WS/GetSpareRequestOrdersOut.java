package com.xframework.model.WS;

import com.xframework.model.SWRequestOrderHead;
import java.util.ArrayList;

public class GetSpareRequestOrdersOut extends BaseWSOut {
    private ArrayList<SWRequestOrderHead> orders = new ArrayList<SWRequestOrderHead>();

    public ArrayList<SWRequestOrderHead> getOrders() {
        return this.orders;
    }

    public void setOrders(ArrayList<SWRequestOrderHead> orders) {
        this.orders = orders;
    }
}
