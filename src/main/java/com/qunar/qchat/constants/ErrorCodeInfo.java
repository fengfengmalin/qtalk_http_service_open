package com.qunar.qchat.constants;

public enum  ErrorCodeInfo {
    ERRORCODEINFO_DEFAULT(-1,"未知错误","unknown error"),
    ERRORCODEINFO_DOMAIN_NOTFIND(10000,"域名输入有误","host error");
    ;

    private int code;
    private String desc_zh;
    private String desc_en;

    ErrorCodeInfo(int code, String desc_zh, String desc_en) {
        this.code = code;
        this.desc_zh = desc_zh;
        this.desc_en = desc_en;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc_zh() {
        return desc_zh;
    }

    public void setDesc_zh(String desc_zh) {
        this.desc_zh = desc_zh;
    }

    public String getDesc_en() {
        return desc_en;
    }

    public void setDesc_en(String desc_en) {
        this.desc_en = desc_en;
    }
}
