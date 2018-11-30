package com.qunar.qchat.dao.model;

/**
 * Created by admin on 17/07/2017.
 */
public class SmsLog {

    private long id;
    private String ip;
    private String tel;
    private String create_time;


    public void setId(long id) {
        this.id = id;
    }
    public long getId(){return this.id;}

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp () {
        return this.ip;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel () {
        return this.tel;
    }


    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time () {
        return this.create_time;
    }

}
