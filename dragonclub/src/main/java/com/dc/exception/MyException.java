package com.dc.exception;

import com.dc.utils.ResultEnum;

/**
 * 自定义异常类
 */
public class MyException extends RuntimeException{
    private Integer code;
    private String message;

    public MyException(Integer code){
        this.code = code;
        this.message = ResultEnum.parse(code);
    }

    public MyException(Integer code , String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
