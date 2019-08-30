package com.cloud.ftl.ftlbasic.webEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("PageBean")
public class PageBean<T> {

    @ApiModelProperty("总页数")
    private Long totalPage;

    @ApiModelProperty("记录总数")
    private Long total;

    @ApiModelProperty("分页列表")
    private List<T> data;

}