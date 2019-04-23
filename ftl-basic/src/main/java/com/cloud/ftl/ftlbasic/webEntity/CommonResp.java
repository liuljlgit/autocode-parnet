package com.cloud.ftl.ftlbasic.webEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResp<T> {

    private Integer code;

    private String msg;

    private List<T> listObject;

    private T object;

    {
        code = CodeEnum.EXEC_OK.getCode();
        msg = CodeEnum.EXEC_OK.getMsg();
    }

    public CommonResp() {

    }

    public CommonResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResp(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public CommonResp(List<T> listObject) {
        this.listObject = listObject;
    }

    public CommonResp(T object) {
        this.object = object;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getListObject() {
        return listObject;
    }

    public void setListObject(List<T> listObject) {
        this.listObject = listObject;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
