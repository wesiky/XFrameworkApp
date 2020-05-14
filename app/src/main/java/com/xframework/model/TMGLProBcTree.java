package com.xframework.model;

import java.io.Serializable;

public class TMGLProBcTree extends BaseModel implements Serializable {
    private String BCTYP;

    private String BHNUM;

    private String CBCID;

    private String IFDEL;

    private String IFSAL;

    private String LBCID;

    private String MATNR;

    private int MJAHR;

    private String STATE;

    public String getBCTYP() {
        return this.BCTYP;
    }

    public String getBHNUM() {
        return this.BHNUM;
    }

    public String getCBCID() {
        return this.CBCID;
    }

    public String getIFDEL() {
        return this.IFDEL;
    }

    public String getIFSAL() {
        return this.IFSAL;
    }

    public String getLBCID() {
        return this.LBCID;
    }

    public String getMATNR() {
        return this.MATNR;
    }

    public int getMJAHR() {
        return this.MJAHR;
    }

    public String getSTATE() {
        return this.STATE;
    }

    public void setBCTYP(String BCTYP) {
        this.BCTYP = BCTYP;
    }

    public void setBHNUM(String BHNUM) {
        this.BHNUM = BHNUM;
    }

    public void setCBCID(String CBCID) {
        this.CBCID = CBCID;
    }

    public void setIFDEL(String IFDEL) {
        this.IFDEL = IFDEL;
    }

    public void setIFSAL(String IFSAL) {
        this.IFSAL = IFSAL;
    }

    public void setLBCID(String LBCID) {
        this.LBCID = LBCID;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public void setMJAHR(int MJAHR) {
        this.MJAHR = MJAHR;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }
}
