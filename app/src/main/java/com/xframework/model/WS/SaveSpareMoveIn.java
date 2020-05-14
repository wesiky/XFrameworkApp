package com.xframework.model.WS;

import com.xframework.model.SWSpareMove;
import java.util.ArrayList;
import java.util.List;

public class SaveSpareMoveIn extends BaseWSIn {
    private List<SWSpareMove> records = new ArrayList<SWSpareMove>();

    public List<SWSpareMove> getRecords() {
        return this.records;
    }

    public void setRecords(List<SWSpareMove> records) {
        this.records = records;
    }
}
