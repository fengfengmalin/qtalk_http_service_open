package com.qunar.qchat.utils;

import com.qunar.qchat.model.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author : mingxing.shao
 * Date : 16-4-12
 * email : mingxing.shao@qunar.com
 */
public class JsonResultUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonResultUtils.class);

    public static JsonResult<?> success() {
        return success(null);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<>(JsonResult.SUCCESS, JsonResult.SUCCESS_CODE, null, data);
    }

    public static JsonResult<?> fail(int errcode, String errmsg) {
        return new JsonResult<>(JsonResult.FAIL, errcode, errmsg, null);
    }
}
