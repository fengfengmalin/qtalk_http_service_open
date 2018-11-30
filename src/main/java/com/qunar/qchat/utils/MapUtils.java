package com.qunar.qchat.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Map;

public class MapUtils {
    public static String getStringValue(Map<String,Object> input,String key,String defaultValue){
        if (null == input)
            return defaultValue;

        if (input.containsKey(key))
            return input.get(key).toString();
        return defaultValue;
    };

    public static int getIntValue(Map<String,Object> input,String key,int  defaultValue){
        if (null == input)
            return defaultValue;

        if (input.containsKey(key)){
            String value = input.get(key).toString();
            Integer integer = Integer.valueOf(value);
            if (null!=integer){
                return integer;
            }
        }
        return defaultValue;
    }

}
