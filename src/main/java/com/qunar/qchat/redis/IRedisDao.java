package com.qunar.qchat.redis;

/**
 * Created by qitmac000378 on 17/5/25.
 */
public interface IRedisDao {
    public boolean setAllStaffDeps(String deps);
    public String getAllStaffDeps();
    public boolean setMobilePushState(String jid, boolean state);
    public boolean getMobilePushState(String jid);
    public boolean getSmsLimit(String jid);
    public boolean setSmsKey(String jid,String smskey);
    public String  getSmsKey(String jid);
    public boolean setCollectionKey(String jid,String bjid);
    public boolean delCollectionKey(String jid,String bjid);

	

}
