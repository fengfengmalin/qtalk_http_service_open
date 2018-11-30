package com.qunar.qchat.dao.model;

public class QCollection {
    private String username;
    private String userhost;
    private String bindname;
    private String bindhost;
    private int flag;
    private String url;
    private String user_name;


    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return this.username;}

    public void setUserhost(String userhost) {this.userhost = userhost;}
    public String getUserhost() {return this.userhost;}

    public void setBindname(String bindname) {this.bindname = bindname;}
    public String getBindname() {return this.bindname;}

    public void setBindhost(String bindhost) {this.bindhost = bindhost;}
    public String getBindhost() {return this.bindhost;}

    public void setFlag(int flag) {this.flag = flag;}
    public int getFlag() {return this.flag;}

    public void setUser_name(String user_name) {this.user_name = user_name;}
    public String getUser_name() {return this.user_name;}

    public void setUrl(String url) {this.url = url;}
    public String getUrl() {return this.url;}
}
