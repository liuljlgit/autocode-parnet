package com.cloud.ftl.ftlbasic.exception;

/**
 * 公用业务异常类
 */
public class BusiException extends RuntimeException {

    private String msg;

    private Integer code;

    public BusiException() {
        super();
    }

    public BusiException(Integer code, Throwable ex) {
        super(null, ex);
        this.code = code;
    }

    public BusiException(String msg, Throwable ex) {
        super(msg, ex);
        this.msg = msg;
    }

    public BusiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusiException(Throwable ex) {
        super(ex);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}