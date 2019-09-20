package com.xframework.model;

import java.io.Serializable;

public class TMGLProBcTree extends BaseModel implements Serializable {
    private int MJAHR;
    private String CBCID;
    private String LBCID;
    private String BCTYP;
    private String STATE;
    private String IFSAL;
    private String IFDEL;
    private String BHNUM;
    private String MATNR;

    public int getMJAHR() {
        return MJAHR;
    }

    public void setMJAHR(int MJAHR) {
        this.MJAHR = MJAHR;
    }

    public String getCBCID() {
        return CBCID;
    }

    public void setCBCID(String CBCID) {
        this.CBCID = CBCID;
    }

    public String getLBCID() {
        return LBCID;
    }

    public void setLBCID(String LBCID) {
        this.LBCID = LBCID;
    }

    public String getBCTYP() {
        return BCTYP;
    }

    public void setBCTYP(String BCTYP) {
        this.BCTYP = BCTYP;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getIFSAL() {
        return IFSAL;
    }

    public void setIFSAL(String IFSAL) {
        this.IFSAL = IFSAL;
    }

    public String getIFDEL() {
        return IFDEL;
    }

    public void setIFDEL(String IFDEL) {
        this.IFDEL = IFDEL;
    }

    public String getBHNUM() {
        return BHNUM;
    }

    public void setBHNUM(String BHNUM) {
        this.BHNUM = BHNUM;
    }

    public String getMATNR() {
        return MATNR;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }
}
