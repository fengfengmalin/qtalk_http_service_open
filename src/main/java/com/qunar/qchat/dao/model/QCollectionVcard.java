package com.qunar.qchat.dao.model;


public class QCollectionVcard {
    private String username;
    private String domain;
    private String usernick;
    private String url;
    private Integer version;


    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return this.username;}

    public void setDomain(String domain) { this.domain = domain;}
    public String getDomain() { return this.domain;}

    public void setUsernick(String usernick) {this.usernick = usernick;}
    public String getUsernick() {return this.usernick;}

    public void setUrl(String url) {this.url = url;}
    public String getUrl() {return this.url;}

    public void setVersion(Integer version) { this.version = version;}
    public Integer getVersion() { return this.version;}
}
