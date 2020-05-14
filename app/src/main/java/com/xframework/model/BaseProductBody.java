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
        byte b = -1;
        StringBuilder stringBuilder19;
        String str19;
        StringBuilder stringBuilder18;
        String str18;
        StringBuilder stringBuilder17;
        String str17;
        StringBuilder stringBuilder16;
        String str16;
        StringBuilder stringBuilder15;
        String str15;
        StringBuilder stringBuilder14;
        String str14;
        StringBuilder stringBuilder13;
        String str13;
        StringBuilder stringBuilder12;
        String str12;
        StringBuilder stringBuilder11;
        String str11;
        StringBuilder stringBuilder10;
        String str10;
        StringBuilder stringBuilder9;
        String str9;
        StringBuilder stringBuilder8;
        String str8;
        StringBuilder stringBuilder7;
        String str7;
        StringBuilder stringBuilder6;
        String str6;
        StringBuilder stringBuilder5;
        String str5;
        StringBuilder stringBuilder4;
        String str4;
        StringBuilder stringBuilder3;
        String str3;
        StringBuilder stringBuilder2;
        String str2;
        String str20 = "";
        String str21 = this.series;
        switch (str21.hashCode()) {
            default:
                b = -1;
                break;
            case 74732896:
                if (str21.equals("NXMSP")) {
                    b = 7;
                    break;
                }
            case 74732888:
                if (str21.equals("NXMSH")) {
                    b = 6;
                    break;
                }
            case 74732668:
                if (str21.equals("NXMLE")) {
                    b = 9;
                    break;
                }
            case 74731722:
                if (str21.equals("NXM-T")) {
                    b = 18;
                    break;
                }
            case 74731709:
                if (str21.equals("NXM-G")) {
                    b = 17;
                    break;
                }
            case 74378059:
                if (str21.equals("NM1LE")) {
                    b = 4;
                    break;
                }
            case 65530232:
                if (str21.equals("DZ20L")) {
                    b = 0;
                    break;
                }
            case 2410736:
                if (str21.equals("NXMS")) {
                    b = 8;
                    break;
                }
            case 2410733:
                if (str21.equals("NXMP")) {
                    b = 13;
                    break;
                }
            case 2410725:
                if (str21.equals("NXMH")) {
                    b = 10;
                    break;
                }
            case 2399280:
                if (str21.equals("NM1B")) {
                    b = 3;
                    break;
                }
            case 2399262:
                if (str21.equals("NM10")) {
                    b = 2;
                    break;
                }
            case 2113876:
                if (str21.equals("DZ20")) {
                    b = 1;
                    break;
                }
            case 77763:
                if (str21.equals("NXM")) {
                    b = 16;
                    break;
                }
            case 77394:
                if (str21.equals("NM1")) {
                    b = 5;
                    break;
                }
            case -1978251404:
                if (str21.equals("NXMP-T")) {
                    b = 15;
                    break;
                }
            case -1978251417:
                if (str21.equals("NXMP-G")) {
                    b = 14;
                    break;
                }
            case -1978259092:
                if (str21.equals("NXMH-T")) {
                    b = 12;
                    break;
                }
            case -1978259105:
                if (str21.equals("NXMH-G")) {
                    b = 11;
                    break;
                }
        }
        switch (b) {
            default:
                return str20.trim();
            case 18:
                stringBuilder19 = new StringBuilder();
                stringBuilder19.append("NXM-");
                stringBuilder19.append(this.frame);
                stringBuilder19.append(this.level);
                stringBuilder19.append("/");
                stringBuilder19.append(this.poles);
                stringBuilder19.append(this.trip_type);
                stringBuilder19.append("00T");
                stringBuilder19.append(this.purpose);
                stringBuilder19.append(this.protect);
                stringBuilder19.append(" ");
                stringBuilder19.append(this.rated_current);
                stringBuilder19.append(" ");
                stringBuilder19.append(this.residual_current);
                stringBuilder19.append(" ");
                stringBuilder19.append(this.interruption_time);
                str19 = stringBuilder19.toString();
            case 17:
                stringBuilder18 = new StringBuilder();
                stringBuilder18.append("NXM-");
                stringBuilder18.append(this.frame);
                stringBuilder18.append(this.level);
                stringBuilder18.append("/");
                stringBuilder18.append(this.poles);
                stringBuilder18.append(this.trip_type);
                stringBuilder18.append("00G");
                stringBuilder18.append(this.purpose);
                stringBuilder18.append(this.protect);
                stringBuilder18.append(" ");
                stringBuilder18.append(this.rated_current);
                stringBuilder18.append(" ");
                stringBuilder18.append(this.residual_current);
                stringBuilder18.append(" ");
                stringBuilder18.append(this.interruption_time);
                str18 = stringBuilder18.toString();
            case 16:
                stringBuilder17 = new StringBuilder();
                stringBuilder17.append(this.series);
                stringBuilder17.append("-");
                stringBuilder17.append(this.frame);
                stringBuilder17.append(this.level);
                stringBuilder17.append("/");
                stringBuilder17.append(this.poles);
                stringBuilder17.append(this.trip_type);
                stringBuilder17.append("00");
                stringBuilder17.append(this.purpose);
                stringBuilder17.append(this.protect);
                stringBuilder17.append(" ");
                stringBuilder17.append(this.rated_current);
                stringBuilder17.append(" ");
                stringBuilder17.append(this.residual_current);
                stringBuilder17.append(" ");
                stringBuilder17.append(this.interruption_time);
                str17 = stringBuilder17.toString();
            case 15:
                stringBuilder16 = new StringBuilder();
                stringBuilder16.append("NXMP-");
                stringBuilder16.append(this.frame);
                stringBuilder16.append(this.level);
                stringBuilder16.append("/");
                stringBuilder16.append(this.poles);
                stringBuilder16.append(this.trip_type);
                stringBuilder16.append("00T");
                stringBuilder16.append(this.purpose);
                stringBuilder16.append(this.protect);
                stringBuilder16.append(" ");
                stringBuilder16.append(this.rated_current);
                stringBuilder16.append(" ");
                stringBuilder16.append(this.residual_current);
                stringBuilder16.append(" ");
                stringBuilder16.append(this.interruption_time);
                str16 = stringBuilder16.toString();
            case 14:
                stringBuilder15 = new StringBuilder();
                stringBuilder15.append("NXMP-");
                stringBuilder15.append(this.frame);
                stringBuilder15.append(this.level);
                stringBuilder15.append("/");
                stringBuilder15.append(this.poles);
                stringBuilder15.append(this.trip_type);
                stringBuilder15.append("00G");
                stringBuilder15.append(this.purpose);
                stringBuilder15.append(this.protect);
                stringBuilder15.append(" ");
                stringBuilder15.append(this.rated_current);
                stringBuilder15.append(" ");
                stringBuilder15.append(this.residual_current);
                stringBuilder15.append(" ");
                stringBuilder15.append(this.interruption_time);
                str15 = stringBuilder15.toString();
            case 13:
                stringBuilder14 = new StringBuilder();
                stringBuilder14.append(this.series);
                stringBuilder14.append("-");
                stringBuilder14.append(this.frame);
                stringBuilder14.append(this.level);
                stringBuilder14.append("/");
                stringBuilder14.append(this.poles);
                stringBuilder14.append(this.trip_type);
                stringBuilder14.append("00");
                stringBuilder14.append(this.purpose);
                stringBuilder14.append(this.protect);
                stringBuilder14.append(" ");
                stringBuilder14.append(this.rated_current);
                stringBuilder14.append(" ");
                stringBuilder14.append(this.residual_current);
                stringBuilder14.append(" ");
                stringBuilder14.append(this.interruption_time);
                str14 = stringBuilder14.toString();
            case 12:
                stringBuilder13 = new StringBuilder();
                stringBuilder13.append("NXMH-");
                stringBuilder13.append(this.frame);
                stringBuilder13.append(this.level);
                stringBuilder13.append("/");
                stringBuilder13.append(this.poles);
                stringBuilder13.append(this.trip_type);
                stringBuilder13.append("00T");
                stringBuilder13.append(this.purpose);
                stringBuilder13.append(this.protect);
                stringBuilder13.append(" ");
                stringBuilder13.append(this.rated_current);
                stringBuilder13.append(" ");
                stringBuilder13.append(this.residual_current);
                stringBuilder13.append(" ");
                stringBuilder13.append(this.interruption_time);
                str13 = stringBuilder13.toString();
            case 11:
                stringBuilder12 = new StringBuilder();
                stringBuilder12.append("NXMH-");
                stringBuilder12.append(this.frame);
                stringBuilder12.append(this.level);
                stringBuilder12.append("/");
                stringBuilder12.append(this.poles);
                stringBuilder12.append(this.trip_type);
                stringBuilder12.append("00G");
                stringBuilder12.append(this.purpose);
                stringBuilder12.append(this.protect);
                stringBuilder12.append(" ");
                stringBuilder12.append(this.rated_current);
                stringBuilder12.append(" ");
                stringBuilder12.append(this.residual_current);
                stringBuilder12.append(" ");
                stringBuilder12.append(this.interruption_time);
                str12 = stringBuilder12.toString();
            case 10:
                stringBuilder11 = new StringBuilder();
                stringBuilder11.append(this.series);
                stringBuilder11.append("-");
                stringBuilder11.append(this.frame);
                stringBuilder11.append(this.level);
                stringBuilder11.append("/");
                stringBuilder11.append(this.poles);
                stringBuilder11.append(this.trip_type);
                stringBuilder11.append("00");
                stringBuilder11.append(this.purpose);
                stringBuilder11.append(this.protect);
                stringBuilder11.append(" ");
                stringBuilder11.append(this.rated_current);
                stringBuilder11.append(" ");
                stringBuilder11.append(this.residual_current);
                stringBuilder11.append(" ");
                stringBuilder11.append(this.interruption_time);
                str11 = stringBuilder11.toString();
            case 9:
                stringBuilder10 = new StringBuilder();
                stringBuilder10.append(this.series);
                stringBuilder10.append("-");
                stringBuilder10.append(this.frame);
                stringBuilder10.append(this.level);
                stringBuilder10.append("/");
                stringBuilder10.append(this.poles);
                stringBuilder10.append(this.trip_type);
                stringBuilder10.append("00");
                stringBuilder10.append(this.purpose);
                stringBuilder10.append(this.protect);
                stringBuilder10.append(" ");
                stringBuilder10.append(this.rated_current);
                stringBuilder10.append(" ");
                stringBuilder10.append(this.residual_current);
                stringBuilder10.append(" ");
                stringBuilder10.append(this.interruption_time);
                str10 = stringBuilder10.toString();
            case 8:
                stringBuilder9 = new StringBuilder();
                stringBuilder9.append(this.series);
                stringBuilder9.append("-");
                stringBuilder9.append(this.frame);
                stringBuilder9.append(this.level);
                stringBuilder9.append("/");
                stringBuilder9.append(this.poles);
                stringBuilder9.append(this.trip_type);
                stringBuilder9.append("00");
                stringBuilder9.append(this.purpose);
                stringBuilder9.append(this.protect);
                stringBuilder9.append(" ");
                stringBuilder9.append(this.rated_current);
                stringBuilder9.append(" ");
                stringBuilder9.append(this.residual_current);
                stringBuilder9.append(" ");
                stringBuilder9.append(this.interruption_time);
                str9 = stringBuilder9.toString();
            case 7:
                stringBuilder8 = new StringBuilder();
                stringBuilder8.append(this.series);
                stringBuilder8.append("-");
                stringBuilder8.append(this.frame);
                stringBuilder8.append(this.level);
                stringBuilder8.append("/");
                stringBuilder8.append(this.poles);
                stringBuilder8.append(this.trip_type);
                stringBuilder8.append("00");
                stringBuilder8.append(this.purpose);
                stringBuilder8.append(this.protect);
                stringBuilder8.append(" ");
                stringBuilder8.append(this.rated_current);
                stringBuilder8.append(" ");
                stringBuilder8.append(this.residual_current);
                stringBuilder8.append(" ");
                stringBuilder8.append(this.interruption_time);
                str8 = stringBuilder8.toString();
            case 6:
                stringBuilder7 = new StringBuilder();
                stringBuilder7.append(this.series);
                stringBuilder7.append("-");
                stringBuilder7.append(this.frame);
                stringBuilder7.append(this.level);
                stringBuilder7.append("/");
                stringBuilder7.append(this.poles);
                stringBuilder7.append(this.trip_type);
                stringBuilder7.append("00");
                stringBuilder7.append(this.purpose);
                stringBuilder7.append(this.protect);
                stringBuilder7.append(" ");
                stringBuilder7.append(this.rated_current);
                stringBuilder7.append(" ");
                stringBuilder7.append(this.residual_current);
                stringBuilder7.append(" ");
                stringBuilder7.append(this.interruption_time);
                str7 = stringBuilder7.toString();
            case 5:
                stringBuilder6 = new StringBuilder();
                stringBuilder6.append(this.series);
                stringBuilder6.append("-");
                stringBuilder6.append(this.frame);
                stringBuilder6.append(this.level);
                stringBuilder6.append("/");
                stringBuilder6.append(this.poles);
                stringBuilder6.append(this.trip_type);
                stringBuilder6.append("00");
                stringBuilder6.append(this.purpose);
                stringBuilder6.append(this.protect);
                stringBuilder6.append(" ");
                stringBuilder6.append(this.rated_current);
                stringBuilder6.append(" ");
                stringBuilder6.append(this.residual_current);
                stringBuilder6.append(" ");
                stringBuilder6.append(this.interruption_time);
                str6 = stringBuilder6.toString();
            case 4:
                stringBuilder5 = new StringBuilder();
                stringBuilder5.append(this.series);
                stringBuilder5.append("-");
                stringBuilder5.append(this.frame);
                stringBuilder5.append(this.level);
                stringBuilder5.append("/");
                stringBuilder5.append(this.poles);
                stringBuilder5.append(this.trip_type);
                stringBuilder5.append("00");
                stringBuilder5.append(this.purpose);
                stringBuilder5.append(this.protect);
                stringBuilder5.append(" ");
                stringBuilder5.append(this.rated_current);
                stringBuilder5.append(" ");
                stringBuilder5.append(this.residual_current);
                stringBuilder5.append(" ");
                stringBuilder5.append(this.interruption_time);
                str5 = stringBuilder5.toString();
            case 3:
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append(this.series);
                stringBuilder4.append("-");
                stringBuilder4.append(this.frame);
                stringBuilder4.append(this.level);
                stringBuilder4.append("/");
                stringBuilder4.append(this.poles);
                stringBuilder4.append(this.trip_type);
                stringBuilder4.append("00");
                stringBuilder4.append(this.purpose);
                stringBuilder4.append(this.protect);
                stringBuilder4.append(" ");
                stringBuilder4.append(this.rated_current);
                stringBuilder4.append(" ");
                stringBuilder4.append(this.residual_current);
                stringBuilder4.append(" ");
                stringBuilder4.append(this.interruption_time);
                str4 = stringBuilder4.toString();
            case 2:
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(this.series);
                stringBuilder3.append("-");
                stringBuilder3.append(this.frame);
                stringBuilder3.append(this.level);
                stringBuilder3.append("/");
                stringBuilder3.append(this.poles);
                stringBuilder3.append(this.trip_type);
                stringBuilder3.append("0");
                stringBuilder3.append(this.purpose);
                stringBuilder3.append(this.protect);
                stringBuilder3.append(" ");
                stringBuilder3.append(this.rated_current);
                stringBuilder3.append(" ");
                stringBuilder3.append(this.residual_current);
                stringBuilder3.append(" ");
                stringBuilder3.append(this.interruption_time);
                str3 = stringBuilder3.toString();
            case 1:
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.series);
                stringBuilder2.append(this.level);
                stringBuilder2.append("-");
                stringBuilder2.append(this.frame);
                stringBuilder2.append("/");
                stringBuilder2.append(this.poles);
                stringBuilder2.append(this.trip_type);
                stringBuilder2.append("00");
                stringBuilder2.append(this.purpose);
                stringBuilder2.append(this.protect);
                stringBuilder2.append(" ");
                stringBuilder2.append(this.rated_current);
                stringBuilder2.append(" ");
                stringBuilder2.append(this.residual_current);
                stringBuilder2.append(" ");
                stringBuilder2.append(this.interruption_time);
                str2 = stringBuilder2.toString();
            case 0:
                break;
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(this.series);
        stringBuilder1.append("-");
        stringBuilder1.append(this.frame);
        stringBuilder1.append(this.level);
        stringBuilder1.append("/");
        stringBuilder1.append(this.poles);
        stringBuilder1.append(this.trip_type);
        stringBuilder1.append("00");
        stringBuilder1.append(this.purpose);
        stringBuilder1.append(this.protect);
        stringBuilder1.append(" ");
        stringBuilder1.append(this.rated_current);
        stringBuilder1.append(" ");
        stringBuilder1.append(this.residual_current);
        stringBuilder1.append(" ");
        stringBuilder1.append(this.interruption_time);
        String str1 = stringBuilder1.toString();
        return str1;
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
