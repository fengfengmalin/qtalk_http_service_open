package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.DailyMarkModule;
import com.qunar.qchat.dao.model.QCollection;
import com.qunar.qchat.dao.model.QCollectionMucVcard;
import com.qunar.qchat.dao.model.QCollectionVcard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IDayliMarkDao {
    public int insertMarkItem(
            @Param("ip") String ip,
            @Param("latitude") String latitude,
            @Param("longitude") String longitude,
            @Param("userid") String userid,
            @Param("domain") String domain,
            @Param("description") String description,
            @Param("location") String location);
    public List<DailyMarkModule> selectMarksInrange(
        @Param("beginDay") String beginDay,
        @Param("endDay") String endDay,
        @Param("userid") String userid,
        @Param("domain") String domain
    );

    public List<DailyMarkModule> selectMarksOneDay(
            @Param("oneDay") String oneDay,
            @Param("userid") String userid,
            @Param("domain") String domain
    );
}
