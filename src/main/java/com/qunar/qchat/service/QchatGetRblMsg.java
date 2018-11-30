package com.qunar.qchat.service;

import com.qunar.qchat.aop.routingdatasource.DataSources;
import com.qunar.qchat.aop.routingdatasource.RoutingDataSource;
import com.qunar.qchat.dao.IGetRblDao;
import com.qunar.qchat.dao.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

/**
 * Created by admin on 14/07/2017.
 *
 *
 */

@Service
public class QchatGetRblMsg {

    private static final Logger LOGGER = LoggerFactory.getLogger(QchatGetMsg.class);

    @Autowired
    private IGetRblDao iGetRblDao;


    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<GetRblMsg> selectRblMsg(String user) {
        return iGetRblDao.selectRblMsg(user);
    }


    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<GetRblMsg> selecRblMucMsg(String user) {
        return iGetRblDao.selecRblMucMsg(user);
    }


}
