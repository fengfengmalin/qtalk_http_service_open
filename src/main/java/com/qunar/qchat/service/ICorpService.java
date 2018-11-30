package com.qunar.qchat.service;

import com.qunar.qchat.dao.model.DailyMarkModule;
import com.qunar.qchat.protocol.responce.userResponce.GetVCardResult;

import java.util.List;

public interface ICorpService {
    public void insertDaylyMark(DailyMarkModule module);
    public List<DailyMarkModule> getMarksInRange(String userid,String domain,String beginday, String endDay);
    public List<DailyMarkModule> getMarksOneDay(String userid,String domain,String day);
}
