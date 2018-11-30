package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.QCollection;
import com.qunar.qchat.dao.model.QCollectionMucVcard;
import com.qunar.qchat.dao.model.QCollectionVcard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ICollectionDao {
    public int  updateBind(
            @Param("username") String username,
            @Param("userhost") String userhost,
            @Param("bindname") String bindname,
            @Param("bindhost") String bindhost,
            @Param("flag") int flag);
    public int insertBind(
            @Param("username") String username,
            @Param("userhost") String userhost,
            @Param("bindname") String bindname,
            @Param("bindhost") String bindhost,
            @Param("flag") int flag);

    public List<QCollection> selectBind(
            @Param("username") String username,
            @Param("userhost") String userhost);

    public List<QCollectionVcard> selectVcard(
            @Param("where") List where);

    public List<QCollectionMucVcard> selectMucVcard(
            @Param("where") List where);
}
