package com.xframework.model;

import java.io.Serializable;

public class BaseWorks  extends BaseModel implements Serializable {
    private int works_id;
    private String works_code;
    private String works_name;
    private String works_description;
    private int works_leader_id;
    private String works_leader_name;

    public int getWorks_id() {
        return works_id;
    }

    public void setWorks_id(int works_id) {
        this.works_id = works_id;
    }

    public String getWorks_code() {
        return works_code;
    }

    public void setWorks_code(String works_code) {
        this.works_code = works_code;
    }

    public String getWorks_name() {
        return works_name;
    }

    public void setWorks_name(String works_name) {
        this.works_name = works_name;
    }

    public String getWorks_description() {
        return works_description;
    }

    public void setWorks_description(String works_description) {
        this.works_description = works_description;
    }

    public int getWorks_leader_id() {
        return works_leader_id;
    }

    public void setWorks_leader_id(int works_leader_id) {
        this.works_leader_id = works_leader_id;
    }

    public String getWorks_leader_name() {
        return works_leader_name;
    }

    public void setWorks_leader_name(String works_leader_name) {
        this.works_leader_name = works_leader_name;
    }
}
