package com.cloud.ftl.ftlbasic.webEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码枚举类
 * @author lijun
 */
public enum CodeEnum {

    EXEC_OK(200,"正确执行"),
    EXEC_ERROR(500, "系统繁忙，请稍后再试！"),
    EXEC_BUSI_ERROR(500, "业务异常，请稍后再试！"),
    EXEC_PARAM_MISS_ERROR(500, "参数缺失"),
    EXEC_FORM_ERROR(500, "表单校验异常"),
    EXEC_VAILD_ERROR(500, "参数校验异常"),
    EXEC_NOT_VAILD_HTTP_ERROR(500, "非法的HTTP请求"),
    EXEC_NOT_SUPPORT_HTTPMETHOD_ERROR(500, "非法的的HTTP METHOD请求"),
    EXEC_NOT_SUPPORT_MEDIATYPE_ERROR(500, "非法的MEDIATYPE请求"),
    EXEC_SQL_ERROR(500, "sql异常"),
    EXEC_UNAUTHORIZED_401(401,"token非法或者已过期"),
    EXEC_FORBIDDEN_403(403,"无访问权限"),
    ;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private static Map<Integer, CodeEnum> map = new HashMap<>();

    static {
        Arrays.stream(CodeEnum.values()).forEach(e -> map.put(e.getCode(), e));
    }


    CodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static CodeEnum getCodeEnum(String code){
        return map.get(code);
    }

}
