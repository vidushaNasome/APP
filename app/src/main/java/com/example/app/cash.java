package com.example.app;

public class cash {
    private String cusid;
    private String cusName;
    private int cusPhone;
    private String cusAddress;
    private String cusCity;

    public cash(){

    }
    public cash(String cusid, String cusName, int cusPhone, String cusAddress, String cusCity) {
        this.cusid = cusid;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusAddress = cusAddress;
        this.cusCity = cusCity;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public int getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(int cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusCity() {
        return cusCity;
    }

    public void setCusCity(String cusCity) {
        this.cusCity = cusCity;
    }
}
