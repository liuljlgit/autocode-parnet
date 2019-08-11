package com.cloud.ftl.ftlbasic.enums;

public enum Opt {

    EQUAL("=", "等于",0),
    NOT_EQUAL("<>", "不等于",1),
    LIKE("like", "像",2),
    NOT_LIKE("not like", "不像",3),
    GREATER(">", "大于",4),
    GREATER_EQUAL(">=", "大于等于",5),
    LESS("<", "小于",6),
    LESS_EQUAL("<=", "小于等于",7),
    IN("in", "在里面",8),
    NOT_IN("not in", "不在里面",9),
    IS_NULL("is null", "是空",10),
    IS("is", "是",11),
    IS_NOT_NULL("is not null", "不是空",12),
    IS_NOT("is not", "不是",13),
    AND("and", "并",14),
    OR("or", "或",15),
    ASC("asc", "顺序",16),
    DESC("desc", "倒序",17),
    BETWEEN("between", "在xx区间",18),
    NOT_BETWEEN("not between", "不在xx区间",19)
    ;

    Opt(String code, String value,Integer index) {
        this.code = code;
        this.value = value;
        this.index = index;
    }

    private String code;

    private String value;

    //这个index主要是用来生成redis key的时候的替代
    private Integer index;

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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
