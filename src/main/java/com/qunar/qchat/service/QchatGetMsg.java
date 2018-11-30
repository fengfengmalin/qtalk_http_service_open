package com.qunar.qchat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qunar.qchat.aop.routingdatasource.DataSources;
import com.qunar.qchat.aop.routingdatasource.RoutingDataSource;
import com.qunar.qchat.dao.IGetMsgDao;
import com.qunar.qchat.dao.model.*;
import com.qunar.qchat.model.JsonResult;
import com.qunar.qchat.utils.HttpClientUtils;
import com.qunar.qchat.utils.JacksonUtils;
import com.qunar.qchat.utils.JsonResultUtils;
import com.qunar.qchat.utils.Xml2Json;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by admin on 14/07/2017.
 *
 *
 */

@Service
public class QchatGetMsg {

    private static final Logger LOGGER = LoggerFactory.getLogger(QchatGetMsg.class);

    @Autowired
    private IGetMsgDao getMsgDao;


    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QGetMsg> get_qchat_msgs(String fuser,String fhost,
                                        String tuser,String thost,
                                        String direction,
                                        String turn,long num,double time) {
        return getMsgDao.selectMsgbyTime(fuser,fhost,tuser,thost,direction,turn,num,time);
    }


    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QGetMsg> get_qchat_history(String user,String host,double time,long num) {
        return getMsgDao.selectQchatHistory(user,host,time,num);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QGetMucMsg> get_qchat_mucmsgs(String muc,String direction,String turn,
                                              long num,double time ) {
        return getMsgDao.selectMucMsgbyTime(muc,direction,turn,num,time);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public Set<QGetMucMsg> get_qhat_muchistory(String user,String host,String domain,double time,long num) {
        return getMsgDao.selectMucHistory(user,host,domain,time,num);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QGetMucMsg> selectLocalDomainMucHistory(String user,String host,double time,long num) {
        return getMsgDao.selectLocalDomainMucHistory(user,host,time,num);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QMucTime> get_qchat_muctime(String user,String host){
        return getMsgDao.selectMucTime(user,host);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<String> get_qchat_muchost(String user,String host){
        return getMsgDao.selectMucHost(user,host);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public String selectMucMinTime(String user,String host){
        return getMsgDao.selectMucMinTime(user,host);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QMucTime> selectMucTime(String user,String host){
        return getMsgDao.selectMucTime(user,host);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QMucTime> selectMucTime1(String user,String host, Double time){
        return getMsgDao.selectMucTime1(user,host, time);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<String> selectMucHost(String user,String host){
        return getMsgDao.selectMucHost(user,host);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<String> selectMucHost1(String user,String host){
        return getMsgDao.selectMucHost1(user,host);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<HashMap<String,Object>> seleectMucDomain(String user,String host){
        return getMsgDao.selectMucDomain(user,host);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QGetMsg> selectConsultMsgbyTime(String from,String fhost,String to, String thost, String virtual,            String real,            String direction,            String turn,
            long num,double time){
        return getMsgDao.selectConsultMsgbyTime(from,fhost,to,thost,virtual,real,direction,turn,num,time);
    }

    @RoutingDataSource(DataSources.QCHAT_SLAVE)
    public List<QMsgReadFlag> get_readflag(String user,Double time, long id){
        return getMsgDao.get_readflag(user,time, id);
    }
}
