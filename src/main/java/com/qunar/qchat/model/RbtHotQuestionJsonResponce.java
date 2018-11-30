package com.qunar.qchat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qitmac000378 on 17/8/10.
 */
public class RbtHotQuestionJsonResponce {
    public List<QuestionItem> questionItemList = new ArrayList<>();
    public static class QuestionItem {
        public QuestionItem() {
            this.itemText = "";
            this.url = "";
        }

        public String itemText;
        public String url;
        public String clickType;
    }
}
