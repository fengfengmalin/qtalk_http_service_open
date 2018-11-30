package com.qunar.qchat.dao;

import com.qunar.qchat.dao.model.PasswordHistoryModule;
import com.qunar.qchat.dao.model.PasswordModule;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 13/07/2017.
 */
@Component
public interface IPasswordDao {

    public void insertPassword(PasswordModule entity);
    public void insertPasswordHistory(PasswordHistoryModule entity);
    public List<PasswordModule> selectPassword(String user, long stime);
    public List<PasswordHistoryModule> selectPasswordHistory(long p_id);

}
