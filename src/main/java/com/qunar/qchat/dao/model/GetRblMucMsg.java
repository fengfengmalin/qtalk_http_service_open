package com.qunar.qchat.dao.model;

/**
 * Created by admin on 17/07/2017.
 */
public class GetRblMucMsg {

    private String msg_id;
    private String groupname;
    private String packet;
    private String create_time;


    public void setMsg_id(String msg_id){
        this.msg_id = msg_id;
    }

    public String getMsg_id(){
        return this.msg_id;
    }

    public void setGroupname(String groupname){
        this.groupname = groupname;
    }

    public String getGroupname(){
        return this.groupname;
    }

    public String getPacket(){
        return this.packet;
    }

    public void setPacket(String packet){
        this.packet = packet;
    }


    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_time () {
        return this.create_time;
    }

}
