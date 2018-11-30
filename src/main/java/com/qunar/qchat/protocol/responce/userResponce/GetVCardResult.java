package com.qunar.qchat.protocol.responce.userResponce;

import com.qunar.qchat.protocol.responce.baseResponce.JsonBaseResult;

import java.util.List;
import java.util.Map;

public class GetVCardResult extends JsonBaseResult {
    public List<VCardGroup> data;

    static public class VCardGroup
    {
        public String domain;
        public List<VCardInfoN> users;
    }

    static public class VCardInfoN
    {
        public String V="";
        public String commenturl="";
        public String type="";
        public String loginName="";
        public String mobile="";
        public String username="";
        public String email="";
        public String imageurl="";
        public Object gender="";
        public String title="";
        public String nickname="";
        public String webname="";
        public List<Map<String,Object>> extentInfo;
    }
}
