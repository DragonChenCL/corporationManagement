package com.dc.utils;

import org.springframework.stereotype.Component;

/**
 * 统一返回数据接口
 */
public class ResponseEntity {

    private Integer code;
    private String message;
    private Object result;

    public ResponseEntity(Integer code, String message, Object result) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public static ResponseEntity res(Integer code,Object result){
        return new ResponseEntity(code,ResultEnum.parse(code),result);
    }

    public static ResponseEntity res(Integer code,String message,Object result){
        return new ResponseEntity(code,message,result);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
