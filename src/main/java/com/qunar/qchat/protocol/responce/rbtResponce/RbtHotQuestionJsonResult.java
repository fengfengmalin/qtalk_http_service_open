package com.qunar.qchat.protocol.responce.rbtResponce;

import com.qunar.qchat.protocol.responce.baseResponce.JsonBaseResult;

import java.util.List;

/**
 * Created by qitmac000378 on 17/8/10.
 */
public class RbtHotQuestionJsonResult extends JsonBaseResult {
    public RbtHotQuestionData data;

    public static class RbtHotQuestionData{
        public List<ListItem> similar_questions;
        public String question;
        public String username;
    }

    public static class RbtHotQuestionAnswer{
        public String answer;
        public String btypes;
        public List<ListItem> default_list;
        public String id;
        public String list_tips;
        public int module_id;
        public String provider;
        public String qtype;
        public String question;
        public String source;
        public String username;
    }

    public static class ListItem{
        public String answer;
        public String feed_time;
        public String id;
        public String qtype;
        //public float  score;
        public List<String> question;
    }
}
