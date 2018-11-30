package com.qunar.qchat.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.qunar.qchat.aop.routingdatasource.DataSources;
import com.qunar.qchat.aop.routingdatasource.RoutingDataSource;
import com.qunar.qchat.dao.IDayliMarkDao;
import com.qunar.qchat.dao.model.DailyMarkModule;
import com.qunar.qchat.service.ICorpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("corpServiceImpl")
public class CorpServiceImpl implements ICorpService {
    @Autowired
    private IDayliMarkDao dayliMarkDao;

    @RoutingDataSource(DataSources.PUBIM_MASTER)
    @Override
    public void insertDaylyMark(DailyMarkModule module) {
        if (null == module)
            return;

        dayliMarkDao.insertMarkItem(
                module.ip,
                module.latitude,
                module.longitude,
                module.userid,
                module.domain,
                module.description,
                module.location);
    }

    @RoutingDataSource(DataSources.PUBIM_MASTER)
    @Override
    public List<DailyMarkModule> getMarksInRange(String userid,String domain,String beginday, String endDay) {
        if (Strings.isNullOrEmpty(beginday) || Strings.isNullOrEmpty(endDay))
            return Lists.newArrayList();

        return dayliMarkDao.selectMarksInrange(userid,domain,beginday, endDay);
    }

    @RoutingDataSource(DataSources.PUBIM_MASTER)
    @Override
    public List<DailyMarkModule> getMarksOneDay(String userid, String domain, String day) {
        if (Strings.isNullOrEmpty(day) || Strings.isNullOrEmpty(day))
            return Lists.newArrayList();

        return dayliMarkDao.selectMarksOneDay(userid,domain,day);
    }
}
