package com.qunar.qchat.protocol.responce.userResponce;

public class BnbVcardResult {
    public int status;
    public Users users;
    public static class Users{
        public String uid;
        public String gender;
        public String nickName;
        public String avatar;
    }
}
