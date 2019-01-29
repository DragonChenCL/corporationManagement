package com.dc.utils;

/**
 * v2.0 自定义状态码维护
 */
public enum ResultEnum {
    SUCCESS(1001,"成功"),
    FAILURE(1002,"失败"),
    USER_NEED_AUTHORITIES(2001,"用户未登录"),
    USER_LOGIN_FAILED(2002,"用户账号或密码错误"),
    USER_LOGIN_SUCCESS(2003,"用户登录成功"),
    REGISTRY_SUCCESS(2004,"用户注册成功"),
    USER_NO_ACCESS(2004,"用户无权访问"),
    USER_LOGOUT_SUCCESS(2005,"用户登出成功"),
    TOKEN_IS_BLACKLIST(2006,"此账号被禁用"),
    LOGIN_IS_OVERDUE(2007,"登录已失效"),
    USER_NOT_EXIST(4001,"用户不存在"),
    UNAUTHORIZED(4002,"认证失败"),
    MISSING_PARAM(4003,"参数传入错误")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @deprecation:通过code返回枚举
     */
    public static String parse(int code){
        ResultEnum[] values = values();
        for (ResultEnum value : values) {
            if(value.getCode() == code){
                return value.getMessage();
            }
        }
        throw  new RuntimeException("Unknown code of ResultEnum");
    }
}
