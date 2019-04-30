package com.cloud.ftl.ftlbasic.enums;

public enum Opt {

    EQUAL("=", "等于"),
    NOT_EQUAL("<>", "不等于"),
    LIKE("like", "像"),
    NOT_LIKE("not like", "不像"),
    GREATER(">", "大于"),
    GREATER_EQUAL(">=", "大于等于"),
    LESS("<", "小于"),
    LESS_EQUAL("<=", "小于等于"),
    IN("in", "在里面"),
    NOT_IN("not in", "不在里面"),
    IS_NULL("is null", "是空"),
    IS("is", "是"),
    IS_NOT_NULL("is not null", "不是空"),
    IS_NOT("is not", "不是"),
    AND("and", "并"),
    OR("or", "或"),
    ASC("asc", "顺序"),
    DESC("desc", "倒序"),
    BETWEEN("between", "在xx区间"),
    NOT_BETWEEN("not between", "不在xx区间")
    ;

    Opt(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
