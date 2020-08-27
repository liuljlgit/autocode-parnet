package ${feignPackagePath};

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import ${entityPackagePath}.${className};
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "文档管理",hidden = true)
@FeignClient(name = "${r'${'}${defaultClientKey}:${defaultClientValue}}")
public interface ${className}Feign {

    @GetMapping(value = "/${className?lower_case}/obj")
    @ApiOperation(value = "根据主键查询" , tags = "xxx", hidden = true, notes = "author: llj")
    @ApiImplicitParam(name="${IdColEntity.fieldJavaName}", value="主键",required = true)
    CommonResp<${className}> selectById(@RequestParam("${IdColEntity.fieldJavaName}") @NotNull ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName});

    @PostMapping(value = "/${className?lower_case}/list")
    @ApiOperation(value = "查询所有列表" , tags = "xxx", hidden = true, notes = "author: llj")
    CommonResp<List<${className}>> selectList(@RequestBody ${className} ${objectName});

    @PostMapping(value = "/${className?lower_case}/page")
    @ApiOperation(value = "分页查询" , tags = "xxx", hidden = true, notes = "author: llj")
    CommonResp<PageBean<${className}>> selectPage(@RequestBody ${className} ${objectName});

    @PostMapping(value = "/${className?lower_case}/obj")
    @ApiOperation(value = "更新或者新增", tags = "xxx", hidden = true, notes = "author: llj")
    CommonResp<Object> save(@RequestBody ${className} ${objectName});

    @DeleteMapping(value = "/${className?lower_case}/obj")
    @ApiOperation(value = "根据主键删除", tags = "xxx", hidden = true, notes = "author: llj")
    @ApiImplicitParam(name="${IdColEntity.fieldJavaName}", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="${IdColEntity.fieldJavaName}") @NotNull ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName});

}
