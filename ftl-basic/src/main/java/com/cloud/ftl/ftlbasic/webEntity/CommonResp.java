package com.cloud.ftl.ftlbasic.webEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("CommonResp")
public class CommonResp<T> {

    @ApiModelProperty("返回代码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("返回实体列表")
    private List<T> listObject;

    @ApiModelProperty("返回单个实体")
    private T object;

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

    public CommonResp(List<T> listObject) {
        this.listObject = listObject;
    }

    public CommonResp(T object) {
        this.object = object;
    }
}
