package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IMucPushDao {
    public List<QMucPush> selectSubscribeFlag(
            @Param("username") String username,
            @Param("host") String host,
            @Param("domain") String domain);


    public List<QMucPush> selectSubscribeFlagbyMuc(
            @Param("username") String username,
            @Param("host") String host,
            @Param("domain") String domain,
            @Param("mucs") String mucs);
}
