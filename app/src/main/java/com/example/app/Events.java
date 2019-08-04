package com.example.app;

public class Events {

    String EVENT,TIME,DATE,MONTH,YEAR;

    public Events (String EVENT,String TIME,String DATE,String MONTH,String YEAR ){

        this.EVENT=EVENT;
        this.TIME=TIME;
        this.DATE=DATE;
        this.MONTH=MONTH;
        this.YEAR=YEAR;
    }

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getDATE() {
        return DATE;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getTIME() {
        return TIME;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getMONTH() {
        return MONTH;
    }
}
