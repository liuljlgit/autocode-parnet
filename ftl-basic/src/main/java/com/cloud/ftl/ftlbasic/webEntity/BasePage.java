package com.cloud.ftl.ftlbasic.webEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "基础分页实体")
public class BasePage {

    @ApiModelProperty("当前页")
    private transient Integer page;

    @ApiModelProperty("分页大小")
    private transient Integer pageSize;

    @ApiModelProperty(hidden = true)
    private transient Integer index;
}
