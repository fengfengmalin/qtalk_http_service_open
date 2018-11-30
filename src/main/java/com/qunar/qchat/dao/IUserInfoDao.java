package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.QCollection;
import com.qunar.qchat.dao.model.QCollectionMucVcard;
import com.qunar.qchat.dao.model.QCollectionVcard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUserInfoDao {
    public int selectUserInfo(
            @Param("username") String username);
}
