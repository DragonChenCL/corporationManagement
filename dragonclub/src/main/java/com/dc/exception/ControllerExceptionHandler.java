package com.dc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器的异常处理类
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Map<String,Object> handlerUserNotExistException(MyException ex)
    {
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("code", ex.getCode());
        result.put("message", ex.getMessage());
        return result;
    }

}
