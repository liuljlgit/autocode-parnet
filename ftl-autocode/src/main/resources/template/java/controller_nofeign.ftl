package ${ctrlPackagePath};

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import ${inftServicePackagePath}.I${className}Service;
import ${entityPackagePath}.${className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/${className?lower_case}")
@Api(tags = "全局接口信息")
public class ${className}Ctrl{

    @Autowired
    private I${className}Service ${objectName}Service;

    @GetMapping(value = "/obj")
    @ApiOperation(value = "根据主键查询" , tags = "xxx",hidden = true, notes = "author: llj")
    @ApiImplicitParam(name="${IdColEntity.fieldJavaName}", value="主键",required = true)
    public CommonResp<${className}> selectById(@RequestParam("${IdColEntity.fieldJavaName}") @NotNull ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) {
        return RespEntity.ok(${objectName}Service.selectById(${IdColEntity.fieldJavaName},"没有符合条件的记录！"));
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询所有列表" , tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<List<${className}>> selectList(@RequestBody ${className} ${objectName}){
        return RespEntity.ok(${objectName}Service.selectList(${objectName}));
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询" , tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<PageBean<${className}>> selectPage(@RequestBody ${className} ${objectName}) {
        return RespEntity.ok(${objectName}Service.selectPage(${objectName}));
    }


    @PostMapping(value = "/obj")
    @ApiOperation(value = "更新或者新增", tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<Object> save(@RequestBody ${className} ${objectName}) {
        ${objectName}Service.save(${objectName});
        return RespEntity.ok();
    }

    @DeleteMapping(value = "/obj")
    @ApiOperation(value = "根据主键删除",tags = "xxx",hidden = true, notes = "author: llj")
    @ApiImplicitParam(name="${IdColEntity.fieldJavaName}", value="主键",required = true)
    public CommonResp<Object> deleteById(@RequestParam(value="${IdColEntity.fieldJavaName}") @NotNull ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) {
        ${objectName}Service.deleteById(${IdColEntity.fieldJavaName});
        return RespEntity.ok();
    }

}
