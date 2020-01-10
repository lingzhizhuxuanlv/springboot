package com.lingzhizhuxuanlv.springboot.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 */
@ControllerAdvice
@Log4j2
public class ExceptionControllerAdvice {

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(Exception e) {
        //记录日志
        log.error("##############################");
        log.error(e);
        log.error("##############################");
        Map<String, Object> map = new HashMap();
        map.put("code", 500);
        map.put("msg", "发生错误");
        return map;
    }

}
