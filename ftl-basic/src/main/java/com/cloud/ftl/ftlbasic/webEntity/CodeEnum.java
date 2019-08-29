package com.cloud.ftl.ftlbasic.webEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码枚举类
 * @author lijun
 */
public enum CodeEnum {

    EXEC_OK(1000,"正确执行"),
    EXEC_ERROR(9999, "系统繁忙，请稍后再试！"),
    EXEC_BUSI_ERROR(-1000, "业务异常，请稍后再试！"),
    EXEC_401(401,"权限确认失败"),
    EXEC_403(403,"无权限访问")
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
