package com.xframework.model.WS;

import com.xframework.model.SWSpareScrap;
import java.util.ArrayList;

public class SaveSpareScrapIn extends BaseWSIn {
    private ArrayList<SWSpareScrap> records = new ArrayList<SWSpareScrap>();

    public ArrayList<SWSpareScrap> getRecords() {
        return this.records;
    }

    public void setRecords(ArrayList<SWSpareScrap> records) {
        this.records = records;
    }
}
