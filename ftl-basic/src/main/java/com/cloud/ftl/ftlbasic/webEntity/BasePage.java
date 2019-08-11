package com.cloud.ftl.ftlbasic.webEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class BasePage {

    /**
     * field comment:当前页
     */
    @JsonIgnore
    public transient Integer page;

    /**
     * field comment:分页大小
     */
    @JsonIgnore
    public transient Integer pageSize;

    /**
     * field comment:limit index,pageSize
     */
    @JsonIgnore
    public transient Integer index;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIndex() {
        if(Objects.isNull(page) || Objects.isNull(pageSize)){
            return null;
        }
        return (page-1)*pageSize;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
