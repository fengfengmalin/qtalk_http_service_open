package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.LeaderboardsItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2018/3/2.
 */

@Component
public interface ILeaderboardsDao {

    public List<LeaderboardsItem> selectDayRankingList(
            @Param("limit") int limit);

    public List<LeaderboardsItem> selectAllRankingList(
            @Param("limit") int limit);

}
