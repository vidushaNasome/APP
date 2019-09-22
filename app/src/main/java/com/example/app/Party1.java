package com.example.app;

public class Party1 {
    private String userName;
    private String telNo;
    private String eventDate;
    private String eventTime;
    private int noOfGuests;

    public Party1(){

    }

    public Party1(String userName, String telNo, String eventDate, String eventTime, int noOfGuests) {
        this.userName = userName;
        this.telNo = telNo;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.noOfGuests = noOfGuests;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public int getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(int noOfGuests) {
        this.noOfGuests = noOfGuests;
    }
}
