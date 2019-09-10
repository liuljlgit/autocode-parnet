package com.cloud.ftl.ftlbasic.webEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 例子: and da_id = 1000
 *
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("查询条件")
public class SqlCondition {

    @ApiModelProperty("连接符")
    private String conStr;

    @ApiModelProperty("操作域")
    private String field;

    @ApiModelProperty("操作符")
    private String optStr;

    @ApiModelProperty("操作值")
    private String values;

}
