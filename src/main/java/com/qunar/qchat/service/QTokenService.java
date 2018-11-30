package com.qunar.qchat.service;

import com.qunar.qchat.aop.routingdatasource.DataSources;
import com.qunar.qchat.aop.routingdatasource.RoutingDataSource;
import com.qunar.qchat.dao.IMacKeyDao;
import com.qunar.qchat.dao.IMucPushDao;
import com.qunar.qchat.dao.model.QMucPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QTokenService {
    @Autowired
    private IMacKeyDao macKeyDao;

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public int updateMacKey(String table, String username,String domain, String mackey, String os,String version)
    {
        return macKeyDao.updateMacKey(table, username, domain, mackey, os, version);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public int updateDelMacKey(String table, String username,String domain, String mackey, String os,String version)
    {
        return macKeyDao.updateDelMacKey(table, username, domain, mackey, os, version);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public int insertMacKey(String table, String username,String domain, String mackey, String os,String version)
    {
        return macKeyDao.insertMacKey(table, username, domain, mackey, os, version);
    }

}
