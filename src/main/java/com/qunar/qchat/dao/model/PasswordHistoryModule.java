package com.qunar.qchat.dao.model;

/**
 * Created by admin on 13/07/2017.
 */
public class PasswordHistoryModule {
    private  long id;
    private String p_id;
    private String p_content;
    private long p_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
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
}
