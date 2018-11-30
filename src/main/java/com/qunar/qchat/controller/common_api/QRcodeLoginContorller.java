package com.qunar.qchat.controller.common_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.qunar.qchat.model.JsonResult;
import com.qunar.qchat.redis.impl.RedisDaoImpl;
import com.qunar.qchat.utils.JacksonUtils;
import com.qunar.qchat.utils.JsonResultUtils;
import com.qunar.qchat.utils.WebAsyncTaskUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by admin on 14/07/2017.
 */

@RequestMapping(value="/qtapi",produces="application/json")
@RestController
public class QRcodeLoginContorller {

    private static final Logger LOGGER = LoggerFactory.getLogger(QRcodeLoginContorller.class);
    private static Integer TIMEOUT = 300;


    @Autowired
    private RedisDaoImpl redisDao;

    /**
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/nck/common/qrcode/startqrcode.qunar", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<?> startQrcode(@RequestBody String json) {
        try {
            LOGGER.info("startQrcode the params is {}", json);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(json, Map.class);

            String qrcodeKey = (String) map.get("qrcodekey");

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("test", "test");
            String value = JacksonUtils.obj2String(resultMap);

            if (redisDao.setQRcodeKey(qrcodeKey, value, 0, TIMEOUT)) {
                return JsonResultUtils.success(1);
            }

            return JsonResultUtils.fail(1, "op fail");
        } catch (Exception e) {
            LOGGER.error("process error", e);
            return JsonResultUtils.fail(2, "process error");
        }
    }


    /**
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/nck/common/qrcode/startqrcodev2.qunar", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<?> startQrcodeV2(@RequestBody String json) {
        try {
            LOGGER.info("startQrcode the params is {}", json);
            ObjectMapper mapper = new ObjectMapper();
            Map mmap = mapper.readValue(json, Map.class);
            String p = mmap.get("p").toString();
            String type = mmap.get("type").toString();

            UUID uuid = UUID.randomUUID();
            String qrcodeKey = uuid.toString().replace("-", "");

            String scheme = "qimlogin://qrcodelogin?k=" + qrcodeKey + "&v=1.0&p=" + p + "&type=" + type;


            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("test", "test");
            String value = JacksonUtils.obj2String(resultMap);

            if (redisDao.setQRcodeKey(qrcodeKey, value, 0, TIMEOUT)) {
                Map<String, Object> data = new HashMap();
                data.put("qrcodekey", qrcodeKey);
                data.put("scheme", scheme);
                return JsonResultUtils.success(data);
            }

            return JsonResultUtils.fail(1, "op fail");
        } catch (Exception e) {
            LOGGER.error("process error", e);
            return JsonResultUtils.fail(2, "process error");
        }
    }


    /**
     *
     * * 取消二维码登陆，由pc端发起
     * url: http://xxxx:xxxx/qtapi/qrcode/cancelqrcode.qunar
     * body: qrcodekey=1111
     * response: {"ret":true,"errcode":0,"errmsg":null,"data":"............."}
     * @param json
     * @return
     */
    @RequestMapping(value = "/nck/common/qrcode/cancelqrcode.qunar", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<?> cancelQrcode(@RequestBody String json) {
        try {
            LOGGER.info("cancelQrcode the params is {}", json);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(json, Map.class);

            String qrcodeKey = (String) map.get("qrcodekey");

            if (redisDao.cancelQRcodeKey(qrcodeKey)) {
                return JsonResultUtils.success(1);
            }

            return JsonResultUtils.fail(1, "op fail");
        } catch (Exception e) {
            LOGGER.error("process error", e);
            return JsonResultUtils.fail(2, "process error");
        }
    }


    /**
     * * PC端获取认证信息
     * url: http://xxxx:xxxx/qtapi/qrcode/checkReady.qunar
     * body: qrcodekey=1111&phase=1
     * response: {"ret":true,"errcode":0,"errmsg":null,"data":"............."}
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/nck/common/qrcode/checkReady.qunar",
            method = RequestMethod.POST)
    public WebAsyncTask checkReady(@RequestBody String json) {

        return WebAsyncTaskUtils.getWebAsyncTaskonTimeOut(5000, new Callable<ModelAndView>() {
            @Override
            public ModelAndView call() throws Exception {

                try {
                    if (Strings.isNullOrEmpty(json))
                        return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(JsonResultUtils.fail(400,"input error"));

                    Map<String,Object> input = JacksonUtils.string2Obj(json,Map.class);


                    String qrCodeKey = input.get("qrcodekey").toString();
                    Integer phase = Integer.valueOf(input.get("phase").toString());

                    int nTime = 20;
                    do {
                        String value = redisDao.getQRcodeKey(qrCodeKey, phase);
                        if (Strings.isNullOrEmpty(value))
                            return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                                    JsonResultUtils.fail(phase, "server error"));

                        Map<String,Object> ret = JacksonUtils.string2Map(value);
                        if (null == ret){
                            return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                                    JsonResultUtils.fail(phase, " data error"));
                        }
                        if (ret.containsKey("t") ) {
                            try {
                                int qrcodetype = Integer.valueOf(ret.get("t").toString());
                                if (1 == qrcodetype || 2 == qrcodetype){
                                    return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                                            JsonResultUtils.success(value));
                                }
                                else {
                                    Thread.sleep(200);
                                    nTime--;
                                }

                            } catch (Exception e){
                                return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                                        JsonResultUtils.fail(phase, "get type error"));
                            }
                        }

                    } while (nTime>0);

                } catch (Exception e) {
                    LOGGER.error("process error", e);
                    return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                            JsonResultUtils.fail(999, "server error"));
                }

                Map<String,Object> vm = Maps.newHashMap();
                vm.put("t",3);
                vm.put("v",1);
                return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                        JsonResultUtils.success(JacksonUtils.obj2String(vm))
                );
            }
        });
    }


    /**
     * * PC端获取认证信息
     * url: http://xxxx:xxxx/qtapi/qrcode/checkReady.qunar
     * body: qrcodekey=1111&phase=1
     * response: {"ret":true,"errcode":0,"errmsg":null,"data":"............."}
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/nck/common/qrcode/checkReadyv2.qunar",
            method = RequestMethod.POST)
    public WebAsyncTask checkReadyv2(@RequestBody String json) {

        return WebAsyncTaskUtils.getWebAsyncTaskonTimeOut(5000, new Callable<ModelAndView>() {
            @Override
            public ModelAndView call() throws Exception {

                try {
                    if (Strings.isNullOrEmpty(json))
                        return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(JsonResultUtils.fail(400,"input error"));

                    Map<String,Object> input = JacksonUtils.string2Obj(json,Map.class);


                    String qrCodeKey = input.get("qrcodekey").toString();
                    Integer phase = Integer.valueOf(input.get("phase").toString());

                    int nTime = 20;
                    do {
                        String value = redisDao.getQRcodeKey(qrCodeKey, phase);
                        if (Strings.isNullOrEmpty(value))
                            return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                                    JsonResultUtils.fail(phase, "server error"));

                        Map<String,Object> ret = JacksonUtils.string2Map(value);
                        if (null == ret){
                            return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                                    JsonResultUtils.fail(phase, " data error"));
                        }
                        if (ret.containsKey("t") ) {
                            try {
                                int qrcodetype = Integer.valueOf(ret.get("t").toString());
                                if (1 == qrcodetype || 2 == qrcodetype){
                                    return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                                            JsonResultUtils.success(value));
                                }
                                else {
                                    Thread.sleep(200);
                                    nTime--;
                                }

                            } catch (Exception e){
                                return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                                        JsonResultUtils.fail(phase, "get type error"));
                            }
                        }

                    } while (nTime>0);

                } catch (Exception e) {
                    LOGGER.error("process error", e);
                    return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                            JsonResultUtils.fail(999, "server error"));
                }

                Map<String,Object> vm = Maps.newHashMap();
                vm.put("t","3");
                vm.put("v","1");
                return WebAsyncTaskUtils.makeModelAndViewWithJsonResult(
                        JsonResultUtils.success(JacksonUtils.obj2String(vm))
                );
            }
        });
    }

    /**
     * url: http://xxxx:xxxx/qtapi/qrcode/auth.qunar≈
     * body: qrcodekey=1111&authdata=xxxx&phase=1
     * response: {"ret":true,"errcode":0,"errmsg":null,"data":"............."}
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/common/qrcode/auth.qunar", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<?> auth(@RequestBody String json) {
        try {
            LOGGER.info("auth the params is {}", json);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(json, Map.class);

            String qrcodeKey = (String) map.get("qrcodekey");
            String authdata = (String) map.get("authdata");
            Integer phase = (Integer) map.get("phase");

            if (redisDao.setQRcodeKey(qrcodeKey, authdata, phase, TIMEOUT)) {
                return JsonResultUtils.success(phase + 1);
            }

            return JsonResultUtils.fail(1, "认证失败");
        } catch (Exception e) {
            LOGGER.error("process error", e);
            return JsonResultUtils.fail(2, "process error");
        }
    }

    @RequestMapping(value = "/common/test.qunar")
    @ResponseBody
    public JsonResult<?> auth2() {
        return JsonResultUtils.success();
    }
}
