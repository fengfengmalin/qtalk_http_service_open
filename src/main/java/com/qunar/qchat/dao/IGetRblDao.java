package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 13/07/2017.
 */
@Component
public interface IGetRblDao {

    public List<GetRblMsg> selectRblMsg(
                @Param("userid") String userid
           );


    public List<GetRblMsg> selecRblMucMsg(
            @Param("user") String user
    );


}
