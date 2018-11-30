package com.qunar.qchat.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IMacKeyDao {
    public int  updateMacKey(
            @Param("table") String table,
            @Param("username") String username,
            @Param("domain") String domain,
            @Param("mackey") String mackey,
            @Param("os") String os,
            @Param("version") String version);
    public int insertMacKey(
            @Param("table") String table,
            @Param("username") String username,
            @Param("domain") String domain,
            @Param("mackey") String mackey,
            @Param("os") String os,
            @Param("version") String version);
    public int  updateDelMacKey(
            @Param("table") String table,
            @Param("username") String username,
            @Param("domain") String domain,
            @Param("mackey") String mackey,
            @Param("os") String os,
            @Param("version") String version);
}