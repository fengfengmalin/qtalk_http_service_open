package com.qunar.qchat.controller.common_api;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.qunar.qchat.constants.QAdverConfig;
import com.qunar.qchat.protocol.responce.userResponce.AdverConfig;
import com.qunar.qchat.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/qtapi/nck/common/ad/")
@RestController
public class QAdverController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QAdverController.class);

    public QAdverController() {

    }

    @RequestMapping(value = "/config.do")
    @ResponseBody
    public String getConfig(
            HttpServletRequest request,
            @RequestParam(value = "debug", required = false, defaultValue = "false") boolean debug,
            @RequestParam(value = "c", required = false, defaultValue = "test1") String domain,
            @RequestParam(value = "p", required = false, defaultValue = "") String platform,
            @RequestParam(value = "v", required = false, defaultValue = "0") Integer clientversion,
            @RequestParam(value = "u", required = false, defaultValue = "") String userid
    ) {

        AdverConfig config = QAdverConfig.getConfig();
        Map<String, Object> result = Maps.newHashMap();

        if (null == config) {
            result.put("shown", false);
            return JacksonUtils.obj2String(result);
        }

        if (!config.data.keySet().contains(platform)) {
            result.put("shown", false);
            return JacksonUtils.obj2String(result);
        }


        Map<String,Object> pltConfig = (Map<String, Object>) config.data.get(platform);
        if (Splitter.on(",").splitToList(pltConfig.get("excludeversion").toString()).contains(String.valueOf(clientversion))) {
            result.put("shown", false);
            return JacksonUtils.obj2String(result);
        }

        if (Integer.valueOf(pltConfig.get("baseversion").toString()) > clientversion) {
            result.put("shown", false);
            return JacksonUtils.obj2String(result);
        }

        result.put("shown", config.shown);
        result.put("adsec", config.adsec);
        result.put("carousel", config.carousel);
        result.put("carouseldelay", config.carouseldelay);
        result.put("allowskip", config.allowskip);
        result.put("version", config.version);
        result.put("skiptips", config.skiptips);
        result.put("adlist", pltConfig.get("data"));
        result.put("interval",config.interval);
        return JacksonUtils.obj2String(result);
    }


}
