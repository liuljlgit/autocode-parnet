package com.cloud.ftl.ftlbasic.webEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("CommonResp")
public class CommonResp {

    @ApiModelProperty("返回代码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("返回数据体")
    private Object body;

    @ApiModelProperty("错误信息集合")
    private Map<String,String> validMap;

    {
        code = CodeEnum.EXEC_OK.getCode();
        msg = CodeEnum.EXEC_OK.getMsg();
    }

    public CommonResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResp(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public CommonResp(Object body) {
        this.body = body;
    }
}
