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
import ${feignPackagePath}.${className}Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
public class ${className}Ctrl implements ${className}Feign {

    @Autowired
    private I${className}Service ${objectName}Service;

    @Override
    public CommonResp<${className}> selectById(@RequestParam("${IdColEntity.fieldJavaName}") @NotNull ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) {
        return RespEntity.ok(${objectName}Service.selectById(${IdColEntity.fieldJavaName},"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<${className}>> selectList(@RequestBody ${className} ${objectName}){
        return RespEntity.ok(${objectName}Service.selectList(${objectName}));
    }

    @Override
    public CommonResp<PageBean<${className}>> selectPage(@RequestBody ${className} ${objectName}) {
        return RespEntity.ok(${objectName}Service.selectPage(${objectName}));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ${className} ${objectName}) {
        ${objectName}Service.save(${objectName});
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="${IdColEntity.fieldJavaName}") @NotNull ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) {
        ${objectName}Service.deleteById(${IdColEntity.fieldJavaName});
        return RespEntity.ok();
    }

}