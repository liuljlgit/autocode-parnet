package com.cloud.ftl.ftlbasic.webEntity;

import java.util.List;

public class PageBean<T> {

    /**
     * field comment:总页数
     */
    private Long totalPage;

    /**
     * field comment:总数
     */
    private Long total;

    /**
     * field comment:分页列表
     */
    private List<T> data;

    public PageBean(){

    }

    public PageBean(Long totalPage, Long total, List<T> data) {
        this.totalPage = totalPage;
        this.total = total;
        this.data = data;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}