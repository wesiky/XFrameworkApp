package com.xframework.model;

import java.io.Serializable;

public class BaseBeltline  extends BaseModel implements Serializable {
    private int beltline_id;
    private String beltline_code;
    private String beltline_name;
    private String beltline_description;
    private int beltline_leader_id;
    private String beltline_leader_name;
    private int works_id;

    public int getBeltline_id() {
        return beltline_id;
    }

    public void setBeltline_id(int beltline_id) {
        this.beltline_id = beltline_id;
    }

    public String getBeltline_code() {
        return beltline_code;
    }

    public void setBeltline_code(String beltline_code) {
        this.beltline_code = beltline_code;
    }

    public String getBeltline_name() {
        return beltline_name;
    }

    public void setBeltline_name(String beltline_name) {
        this.beltline_name = beltline_name;
    }

    public String getBeltline_description() {
        return beltline_description;
    }

    public void setBeltline_description(String beltline_description) {
        this.beltline_description = beltline_description;
    }

    public int getBeltline_leader_id() {
        return beltline_leader_id;
    }

    public void setBeltline_leader_id(int beltline_leader_id) {
        this.beltline_leader_id = beltline_leader_id;
    }

    public String getBeltline_leader_name() {
        return beltline_leader_name;
    }

    public void setBeltline_leader_name(String beltline_leader_name) {
        this.beltline_leader_name = beltline_leader_name;
    }

    public int getWorks_id() {
        return works_id;
    }

    public void setWorks_id(int works_id) {
        this.works_id = works_id;
    }
}
