package com.qunar.qchat.dao.model;

/**
 * Created by MSI on 2018/1/31.
 */
public class QIplimit {
    private String ip;
    private String name;
    private String priority;

    public void setIp(String ip){
        this.ip = ip;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPriority(String priority){
        this.priority = priority;
    }

    public String getIp(){
        return ip;
    }

    public String getName(){
        return name;
    }
    public String getPriority(){
        return priority;
    }

}
