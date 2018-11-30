package com.qunar.qchat.protocol.responce.userResponce;

import com.qunar.qchat.constants.QAdverConfig;

import java.util.Map;


public class AdverConfig {
    public int version;

    public int adsec;
    public boolean shown;
    public boolean carousel;
    public int carouseldelay;
    public boolean allowskip;
    public String skiptips;
    public int interval;
    public Map<String, Object> data;

    public static class AdverPltConfig {
        public String baseversion;
        public String excludeversion;
        public Map<String, Object> data;
    }

    public static class AdverItem {
        public String adtype;
        public String linkurl;
    }
}
