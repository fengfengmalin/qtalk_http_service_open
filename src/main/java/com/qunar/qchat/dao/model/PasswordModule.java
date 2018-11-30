package com.qunar.qchat.dao.model;

/**
 * Created by admin on 13/07/2017.
 */
public class PasswordModule {
    private String p_id;
    private String p_user;
    private String p_title;
    private String p_introduce;
    private String p_content;
    private long p_time;

    public String getP_id() { return p_id; }

    public String getP_user() {
        return p_user;
    }

    public void setP_user(String p_user) {
        this.p_user = p_user;
    }

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
    }

    public String getP_introduce() {
        return p_introduce;
    }

    public void setP_introduce(String p_introduce) {
        this.p_introduce = p_introduce;
    }

    public String getP_content() {
        return p_content;
    }

    public void setP_content(String p_content) {
        this.p_content = p_content;
    }

    public long getP_time() {
        return p_time;
    }

    public void setP_time(long p_time) {
        this.p_time = p_time;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }
}
