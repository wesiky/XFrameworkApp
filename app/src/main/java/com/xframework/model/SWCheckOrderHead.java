package com.xframework.model;

import java.util.Date;

public class SWCheckOrderHead extends BaseOrderHead {
    private String check_allocation_ids;

    private Date check_date;

    private int check_type;

    public String getCheck_allocation_ids() {
        return this.check_allocation_ids;
    }

    public Date getCheck_date() {
        return this.check_date;
    }

    public int getCheck_type() {
        return this.check_type;
    }

    public void setCheck_allocation_ids(String check_allocation_ids) {
        this.check_allocation_ids = check_allocation_ids;
    }

    public void setCheck_date(Date check_date) {
        this.check_date = check_date;
    }

    public void setCheck_type(int check_type) {
        this.check_type = check_type;
    }
}
