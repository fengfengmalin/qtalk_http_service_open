package com.qunar.qchat.requestbody;

import java.util.List;

public class GetVCardData {
    public List<UserVCardInfo> users;
    public String domain;
    public static class UserVCardInfo
    {
        public String user;
        public String version;
    }
}
