package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.UserModule;
import com.qunar.qchat.dao.model.VirtualUserModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MSI on 2018/1/18.
 */
@Component

public interface IUpdateOrganizationDao {
    public void insertHostUser(@Param("host_id") int host_id,
                                 @Param("user_id") String user_id,
                                 @Param("user_name") String user_name,
                                 @Param("department") String department,
                                 @Param("dep1") String dep1,
                                 @Param("dep2") String dep2,
                                 @Param("dep3") String dep3,
                                 @Param("dep4") String dep4,
                                 @Param("dep5") String dep5,
                                 @Param("user_type") String user_type,
                                 @Param("hire_flag") int hire_flag,
                                 @Param("gender") int gender,
                                 @Param("password") String password,
                                 @Param("ps_deptid") String ps_deptid,
                                 @Param("version") int version

    );

    public String getMaxVersion(@Param("host_id") int host_id);
}
