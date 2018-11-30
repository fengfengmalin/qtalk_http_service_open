package com.qunar.qchat.service;

import com.qunar.qchat.aop.routingdatasource.DataSources;
import com.qunar.qchat.aop.routingdatasource.RoutingDataSource;
import com.qunar.qchat.dao.IMucPushDao;
import com.qunar.qchat.dao.model.QMucPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QMucPushService {
    @Autowired
    private IMucPushDao mucPushDao;

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QMucPush> get_subscribe_flag(String username, String host, String domain)
    {
        return mucPushDao.selectSubscribeFlag(username, host, domain);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QMucPush> get_subscribe_flag_by_muc(String username, String host, String domain,String mucs)
    {
        return mucPushDao.selectSubscribeFlagbyMuc(username, host, domain,mucs);
    }

}
