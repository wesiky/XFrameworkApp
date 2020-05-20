package com.xframework.model;

import java.io.Serializable;

public class BaseProductBody extends BaseModel implements Serializable {
    private String accessories = "";

    private String frame = "";

    private String interruption_time = "";

    private String level = "";

    private String poles = "";

    private String protect = "";

    private String purpose = "";

    private String rated_current = "";

    private String residual_current = "";

    private String series = "";

    private String trip_type = "";

    public String GetProductName() {
        String productName;
        switch (series) {
            case "NXMSP":
                productName = "NXMSP-" + frame + level + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXMSH":
                productName = "NXMSH-" + frame + level + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXMLE":
                productName = "NXMLE-" + frame + level + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXM-T":
                productName = "NXM-" + frame + level + "/" + poles +trip_type + "00T" + purpose + protect + " " + residual_current + interruption_time;
                break;
            case "NXM-G":
                productName = "NXM-" + frame + level +"/" + poles + trip_type + "00G" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                break;
            case "NM1LE":
                productName = "NM1LE-" + frame + level + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "DZ20L":
                productName = "DZ20L-" + frame + level + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXMS":
                productName = "NXMS-" + frame + level + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " +interruption_time;
                    break;
            case "NXMP":
                productName = "NXMP-" + frame +level + "/" + poles +trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXMH":
                productName = "NXMH-" + frame + level + "/" + poles + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NM1B":
                productName = "NM1B-" + frame + level + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NM10":
                productName = "NM10-" + frame + level + "/" + poles + trip_type + "0" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "DZ20":
                productName = "DZ20" + level + "-" + frame + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXM":
                productName = "NXM-" + frame +level +"/" + poles + trip_type +"00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NM1":
                productName = "NM1-" + frame + level + "/" + poles + trip_type + "00" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXMP-T":
                productName = "NXMP-" + frame +level + "/" + poles + trip_type +"00T" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXMP-G":
                productName = "NXMP-" + frame + level +"/" + poles +trip_type + "00G" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXMH-T":
                productName = "NXMH-" + frame +level +"/" + poles + trip_type + "00T" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            case "NXMH-G":
                productName = "NXMH-" + frame + level + "/" + poles + trip_type + "00G" + purpose + protect + " " + rated_current + " " + residual_current + " " + interruption_time;
                    break;
            default:
                return "";
        }
        return productName;
    }

    public String getAccessories() {
        return this.accessories;
    }

    public String getFrame() {
        return this.frame;
    }

    public String getInterruptionTime() {
        return this.interruption_time;
    }

    public String getLevel() {
        return this.level;
    }

    public String getPoles() {
        return this.poles;
    }

    public String getProtect() {
        return this.protect;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public String getRatedCurrent() {
        return this.rated_current;
    }

    public String getResidualCurrent() {
        return this.residual_current;
    }

    public String getSeries() {
        return this.series;
    }

    public String getTripType() {
        return this.trip_type;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setInterruptionTime(String interruption_time) {
        this.interruption_time = interruption_time;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setPoles(String poles) {
        this.poles = poles;
    }

    public void setProtect(String protect) {
        this.protect = protect;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setRatedCurrent(String rated_current) {
        this.rated_current = rated_current;
    }

    public void setResidualCurrent(String residual_current) {
        this.residual_current = residual_current;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setTripType(String trip_type) {
        this.trip_type = trip_type;
    }
}
