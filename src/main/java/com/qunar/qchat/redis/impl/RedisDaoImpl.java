package com.qunar.qchat.redis.impl;

import com.qunar.qchat.redis.IRedisDao;
import com.qunar.qchat.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qitmac000378 on 17/5/25.
 */
public class RedisDaoImpl extends AbstractBaseRedisDao<String,String> implements IRedisDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDaoImpl.class);

    private String ALLSTAFFDEPS_KEY = "qim.allstaff.deps";
    @Override
    public boolean setAllStaffDeps(final String deps) {

        if (null == redisTemplate)
            return false;
       // ((JedisConnectionFactory) redisTemplate.getConnectionFactory()).setDatabase(6);

        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(6);

                RedisSerializer<String> serializer = getRedisSerializer();
                String tdep = null==deps?"":deps;
                byte[] key  = serializer.serialize(ALLSTAFFDEPS_KEY);
                byte[] value = serializer.serialize(tdep);
                // 一天过期
                connection.setEx(key,60*60*24, value);
                return true;
            }
        }, false, true);
        return result;
    }

    @Override
    public String getAllStaffDeps() {
        String result = "" ;
        if (null!=redisTemplate) {
//            ((JedisConnectionFactory)redisTemplate.getConnectionFactory()).setDatabase(6);

             result = redisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    connection.select(6);
                    RedisSerializer<String> serializer = getRedisSerializer();
                    byte[] key = serializer.serialize(ALLSTAFFDEPS_KEY);
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    String deps = serializer.deserialize(value);
                    return deps;
                }
            });
        }
        return result;
    }

    private String MOBILE_PUSH_KEY_Prefix = "mp_";
    @Override
    public boolean setMobilePushState(final String jid,final boolean state) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(10);
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(MOBILE_PUSH_KEY_Prefix+jid);
                byte[] value = serializer.serialize(state?"1":"0");
                // 一天过期
                connection.set(key,value);
                return true;
            }
        }, false, true);
        return result;
    }

    @Override
    public boolean getMobilePushState(final String jid){
        boolean result = false ;
        if (null!=redisTemplate) {
            result = redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    connection.select(10);
                    RedisSerializer<String> serializer = getRedisSerializer();
                    byte[] key = serializer.serialize(MOBILE_PUSH_KEY_Prefix+jid);
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return false;
                    }
                    String state = serializer.deserialize(value);
                    return state.equals("1")?true:false;
                }
            });
        }
        return result;
    }

    @Override
    public boolean getSmsLimit(final String jid) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(1);

                RedisSerializer<String> serializer = getRedisSerializer();

                String sjid = null==jid?"":jid+"_count";
                byte[] ckey  = serializer.serialize(sjid);
                byte[] count = connection.get(ckey);
                long time = System.currentTimeMillis() /1000;
                long stime = null==count? 0:Long.valueOf(serializer.deserialize(count));

                if (stime + 60 < time ) {
                    connection.setEx(ckey, 60 * 1, serializer.serialize(Long.toString(time)));
                    return true;
                }
                return false;
            }
        }, false, true);
        return result;
    }

    @Override
    public boolean setSmsKey(final String jid,final String smskey) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(1);

                RedisSerializer<String> serializer = getRedisSerializer();

                String rjid = null == jid ? "" : jid + "_skey";
                byte[] key = serializer.serialize(rjid);
                byte[] value = serializer.serialize(smskey);
                    // 5min
                connection.setEx(key,  60 * 5, value);
                return true;
            }
        }, false, true);
        return result;
    }

    public String getSmsKey(final String jid){
        String result = "" ;
        if (null!=redisTemplate) {

            result = redisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    connection.select(1);
                    RedisSerializer<String> serializer = getRedisSerializer();
                    byte[] key = serializer.serialize(jid + "_skey");
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    String val = serializer.deserialize(value);
                    return val;
                }
            });
        }
        return result;
    }

@Override
    public boolean setCollectionKey(String jid,String bjid)
    {
        boolean result = false;
        if (null!=redisTemplate) {

            result = redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    connection.select(3);

                    RedisSerializer<String> serializer = getRedisSerializer();

                    String cjid = null == jid ? "" : "collection_key:" + jid;
                    byte[] key = serializer.serialize(cjid);
                    byte[] key1 = serializer.serialize(bjid);
                    byte[] value = serializer.serialize("1");


                    Boolean ret = false;
                    ret = connection.hSet(key, key1, value);

                    return ret == null;
                }
            }, false, true);
        }
        return result;
    }

    public boolean delCollectionKey(String jid,String bjid)
    {
        String  result = "";
        if (null!=redisTemplate) {

            result  = redisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    connection.select(3);
                    RedisSerializer<String> serializer = getRedisSerializer();
                    byte[] key = null == jid ? serializer.serialize(""):serializer.serialize("collection_key:" + jid);
                    byte[] bkey = null == bjid ? serializer.serialize(""):serializer.serialize(bjid);

                    return String.valueOf(connection.hDel(key,bkey));

                }
            });
        }
        return "1" == result;
    }

    public boolean setQRcodeKey(String key,String value, Integer phase, Integer timeout)
    {
        try {
            if (null != redisTemplate) {

                Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
                    public Boolean doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        connection.select(6);

                        RedisSerializer<String> serializer = getRedisSerializer();

                        String key1 = null == key ? "" : "qrcode:" + key;
                        byte[] key2 = serializer.serialize(key1);
                        byte[] value1 = serializer.serialize(value);
                        byte[] fkey = serializer.serialize(phase.toString());
                        Integer bphase = phase - 1;
                        byte[] fkey1 = serializer.serialize(bphase.toString());

                        if (phase == 0) {
                            connection.expire(key2, 0);
                            connection.hSet(key2, fkey, value1);
                            connection.expire(key2, timeout);

                            return true;
                        } else if (phase > 0) {
                            byte[] tvalue1 = connection.hGet(key2, fkey1);
                            if (tvalue1 == null) {
                                return false;
                            }
                            connection.expire(key2, 0);
                            connection.hSet(key2, fkey, value1);
                            connection.expire(key2, timeout);

                            return true;
                        }
                        return false;
                    }
                }, false, false);

                return result;
            }

            return false;
        } catch  (Exception e){
            LOGGER.error("process error",e);
            return false;
        }
    }

    public boolean cancelQRcodeKey(String key)
    {
        try {
            if (null != redisTemplate) {

                Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
                    public Boolean doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        connection.select(6);

                        RedisSerializer<String> serializer = getRedisSerializer();

                        String key1 = null == key ? "" : "qrcode:" + key;
                        byte[] key2 = serializer.serialize(key1);

                        connection.expire(key2, 0);

                        return true;
                    }
                }, false, false);

                return result;
            }

            return false;
        } catch  (Exception e){
            LOGGER.error("process error",e);
            return false;
        }
    }

    public String getQRcodeKey(String key,Integer phase)
    {
        try {
            if (null != redisTemplate) {

                String result = redisTemplate.execute(new RedisCallback<String>() {
                    public String doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        connection.select(6);

                        RedisSerializer<String> serializer = getRedisSerializer();

                        String key1 = null == key ? "" : "qrcode:" + key;
                        byte[] key2 = serializer.serialize(key1);
                        byte[] fkey = serializer.serialize(phase.toString());
                        byte[] tvalue = connection.hGet(key2, fkey);
                        Integer bphase = phase - 1;
                        byte[] fkey1 = serializer.serialize(bphase.toString());

                        if (tvalue == null) {
                            byte[] tvalue1 = connection.hGet(key2, fkey1);
                            if(tvalue1 == null) {
                                return qrcodeInvalid();
                            }

                            return qrcodeNotAuth();
                        }

                        if (phase == 2) {
                            connection.expire(key2, 0);
                        }

                        return serializer.deserialize(tvalue);
                    }
                }, false, false);

                return result;
            }

            return null;
        } catch  (Exception e){
            LOGGER.error("process error",e);
            return null;
        }
    }

    private String qrcodeInvalid() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("t", "2");
        resultMap.put("v", "1");
        return JacksonUtils.obj2String(resultMap);
    }

    private String qrcodeNotAuth() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("t", "3");
        resultMap.put("v", "1");
        return JacksonUtils.obj2String(resultMap);
    }
}
