package com.qunar.qchat.controller.qtalk_api;

import com.qunar.qchat.model.JsonResult;
import com.qunar.qchat.utils.JsonResultUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/qtapi/base/")
@RestController
public class QBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QBaseController.class);

    @RequestMapping(value = "/getservertime.qunar", method = RequestMethod.GET)
    public JsonResult<?> get_servertime(HttpServletRequest request) {
        try {
            long time = System.currentTimeMillis()/1000;
            return JsonResultUtils.success(time);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug("catch error ", e);
            return JsonResultUtils.fail(0, "服务器操作异常:\n " + ExceptionUtils.getStackTrace(e));
        }
    }
}
