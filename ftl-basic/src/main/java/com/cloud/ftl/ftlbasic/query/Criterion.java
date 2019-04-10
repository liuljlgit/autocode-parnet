package com.cloud.ftl.ftlbasic.query;

/**
 * 这个类相当于一个具体的查询条件
 */
public class Criterion {
    private String condition;
    private Object value1;
    private Object value2;
    private Object list;
    private boolean noValue;
    private boolean oneValue;
    private boolean secondValue;
    private boolean listValue;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Object getValue1() {
        return value1;
    }

    public void setValue1(Object value1) {
        this.value1 = value1;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public boolean isNoValue() {
        return noValue;
    }

    public void setNoValue(boolean noValue) {
        this.noValue = noValue;
    }

    public boolean isOneValue() {
        return oneValue;
    }

    public void setOneValue(boolean oneValue) {
        this.oneValue = oneValue;
    }

    public boolean isSecondValue() {
        return secondValue;
    }

    public void setSecondValue(boolean secondValue) {
        this.secondValue = secondValue;
    }

    public boolean isListValue() {
        return listValue;
    }

    public void setListValue(boolean listValue) {
        this.listValue = listValue;
    }
}
