package com.xframework.model.WS;

import com.xframework.model.BaseOrderBody;
import java.util.ArrayList;

public class GetSpareOrderDetailsOut extends BaseWSOut {
    private ArrayList<BaseOrderBody> details = new ArrayList<BaseOrderBody>();

    public ArrayList<BaseOrderBody> getDetails() {
        return this.details;
    }

    public void setDetails(ArrayList<BaseOrderBody> details) {
        this.details = details;
    }
}
