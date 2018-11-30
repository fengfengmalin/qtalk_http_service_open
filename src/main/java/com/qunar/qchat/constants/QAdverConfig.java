package com.qunar.qchat.constants;

import com.google.common.base.Strings;
import com.qunar.qchat.protocol.responce.userResponce.AdverConfig;
import com.qunar.qchat.utils.JacksonUtils;
import org.springframework.stereotype.Component;

@Component
public class QAdverConfig {
    private static AdverConfig config = null;

    public void onChange(String configcontent) {
        if (!Strings.isNullOrEmpty(configcontent)) {
            config = JacksonUtils.string2Obj(JacksonUtils.obj2String(JacksonUtils.string2Map(configcontent)),AdverConfig.class);
            int a = 10;
            a = 20;
        }
    }

    public static AdverConfig getConfig() {
        return config;
    }
}
