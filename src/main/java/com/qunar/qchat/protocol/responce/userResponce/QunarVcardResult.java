package com.qunar.qchat.protocol.responce.userResponce;

import com.qunar.qchat.protocol.responce.baseResponce.JsonBaseResult;

import java.util.List;

public class QunarVcardResult extends JsonBaseResult {
    public List<QunarUser> data;
    public static class QunarUser{
        public String username;
        public int ver;
        public int errcode;
        public int gender;
        public int type;
        public String imageurl;
        public String loginName;
        public String nickname;
        public String webname;
    }
    public static class QunarUserExtentInfo{
    }
}
