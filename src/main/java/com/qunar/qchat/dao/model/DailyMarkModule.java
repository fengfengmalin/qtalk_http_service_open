package com.qunar.qchat.dao.model;

public class DailyMarkModule {
    public String ip;
    public String longitude;
    public String latitude;
    public String userid;
    public String domain;
    public String description;
    public String create_time;
    public String location;
    public String day;
    public Long timestamp;
    public DailyMarkModule() {
        ip = "0.0.0.0";
        longitude = "";
        latitude = "";
        userid = "";
        domain = "";
        description = "";
        create_time = "";
        location = "";
        timestamp = 0L;
    }
}
