package com.qunar.qchat.utils;

import com.qunar.qchat.model.JsonResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.concurrent.Callable;

public class WebAsyncTaskUtils {
    public static WebAsyncTask getWebAsyncTaskonTimeOut(long timeout, Callable<ModelAndView> worker){
        WebAsyncTask asyncTask = new WebAsyncTask(timeout,worker);
        asyncTask.onTimeout(new Callable<ModelAndView>() {
            @Override
            public ModelAndView call() throws Exception {
                return makeModelAndViewWithJsonResult(JsonResultUtils.fail(-1,"time out "));
            }
        });

        return  asyncTask;
    }

    public static<T> ModelAndView makeModelAndViewWithJsonResult(JsonResult<T> jsonResult){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("ret", jsonResult.isRet());
        mav.addObject("errcode",jsonResult.getErrcode());
        mav.addObject("errmsg",jsonResult.getErrmsg());
        mav.addObject("data",jsonResult.getData());
        return mav;
    }

}
