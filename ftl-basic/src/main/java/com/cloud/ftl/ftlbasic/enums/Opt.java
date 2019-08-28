package com.cloud.ftl.ftlbasic.enums;

import com.google.common.collect.Sets;

import java.util.Set;

public enum Opt {

    EQUAL(" = ", "等于",0),
    NOT_EQUAL(" <> ", "不等于",1),
    LIKE(" like ", "像",2),
    NOT_LIKE(" not like ", "不像",3),
    GREATER(" > ", "大于",4),
    GREATER_EQUAL(" >= ", "大于等于",5),
    LESS(" < ", "小于",6),
    LESS_EQUAL(" <= ", "小于等于",7),
    IN(" in ", "在里面",8),
    NOT_IN(" not in ", "不在里面",9),
    IS_NULL(" is null ", "是",10),
    IS_NOT_NULL(" is not null ", "不是",11),
    AND(" and ", "并",12),
    OR(" or ", "或",13),
    ASC(" asc ", "顺序",14),
    DESC(" desc ", "倒序",15),
    BETWEEN(" between ", "在xx区间",16),
    NOT_BETWEEN(" not between ", "不在xx区间",17)
    ;

    Opt(String code, String value,Integer index) {
        this.code = code;
        this.value = value;
        this.index = index;
    }

    private String code;

    private String value;

    private Integer index;

    static Set<Opt> oneValueOptSet;

    static {
        oneValueOptSet = Sets.newHashSet();
        oneValueOptSet.add(Opt.EQUAL);
        oneValueOptSet.add(Opt.NOT_EQUAL);
        oneValueOptSet.add(Opt.LIKE);
        oneValueOptSet.add(Opt.NOT_LIKE);
        oneValueOptSet.add(Opt.GREATER);
        oneValueOptSet.add(Opt.GREATER_EQUAL);
        oneValueOptSet.add(Opt.LESS);
        oneValueOptSet.add(Opt.LESS_EQUAL);
        oneValueOptSet.add(Opt.IN);
        oneValueOptSet.add(Opt.NOT_IN);
    }

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

    public Set<Opt> getOneValueOptSet() {
        return oneValueOptSet;
    }

    public void setOneValueOptSet(Set<Opt> oneValueOptSet) {
        Opt.oneValueOptSet = oneValueOptSet;
    }
}
