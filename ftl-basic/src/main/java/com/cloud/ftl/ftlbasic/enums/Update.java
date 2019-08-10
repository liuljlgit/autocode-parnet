package com.cloud.ftl.ftlbasic.enums;

public enum Update {

    NOT_NULL("NOT_NULL", "更新不为空的字段"),
    WITH_NULL("WITH_NULL", "全部更新，置空的也更新");

    private String code;

    private String desc;

    Update(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
