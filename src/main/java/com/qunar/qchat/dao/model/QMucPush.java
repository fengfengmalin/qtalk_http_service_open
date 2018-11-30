package com.qunar.qchat.dao.model;

public class QMucPush {
    private String muc_name;
    private String subscribe_flag;
    private String domain;

    public void setMuc_name(String muc_name) {this.muc_name = muc_name;}
    public String getMuc_name() {return this.muc_name;}

    public void setSubscribe_flag(String subscribe_flag) {this.subscribe_flag = subscribe_flag;}
    public String getSubscribe_flag() {return this.subscribe_flag;}

    public void setDomain(String domain) {this.domain = domain;}
    public String getDomain() {return this.domain;}
}
