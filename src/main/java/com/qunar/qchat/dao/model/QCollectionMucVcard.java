package com.qunar.qchat.dao.model;

public class QCollectionMucVcard {
    private String muc_name;
    private String show_name;
    private String muc_desc;
    private String muc_title;
    private String muc_pic;
    private Integer version;


    public void setMuc_name(String muc_name) {this.muc_name = muc_name;}
    public String getMuc_name() {return this.muc_name;}

    public void setShow_name(String show_name) { this.show_name = show_name;}
    public String getShow_name() { return this.show_name;}

    public void setMuc_desc(String muc_desc) {this.muc_desc = muc_desc;}
    public String getMuc_desc() {return this.muc_desc;}

    public void setMuc_title(String muc_title) {this.muc_title = muc_title;}
    public String getMuc_title() {return this.muc_title;}

    public void setMuc_pic(String muc_pic) { this.muc_pic = muc_pic;}
    public String getMuc_pic() { return this.muc_pic;}

    public void setVersion(Integer version) { this.version = version;}
    public Integer getVersion() { return this.version;}
}
