package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * Created by admin on 13/07/2017.
 */
@Component
public interface ISmsLog {

    public void insertSmsLog(@Param("s_ip") String ip,
                             @Param("s_tel") String  tel);

}
