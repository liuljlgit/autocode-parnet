package ${ctrlPackagePath};

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import ${inftServicePackagePath}.I${className}Service;
import ${entityPackagePath}.${className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

/**
 * ${className}Ctrl 控制层方法
 * @author lijun
 */
@Slf4j
@RestController
@RequestMapping("/${className?lower_case}")
public class ${className}Ctrl{

    @Autowired
    private I${className}Service ${objectName}Service;

    /**
    * ${className} 根据主键获取单个数据
    * @return
    * @throws Exception
    */
    @GetMapping(value = "/obj")
    public CommonResp<${className}> selectById(${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) {
        if(Objects.isNull(${IdColEntity.fieldJavaName})){
            throw new BusiException("请输入要获取的数据的ID") ;
        }
        return RespEntity.ok(${objectName}Service.selectById(${IdColEntity.fieldJavaName},"没有符合条件的记录！"));
    }

   /**
    * ${className} 根据实体对象查询所有列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/list")
    public CommonResp<${className}> selectList(@RequestBody ${className} entity){
        return RespEntity.ok(${objectName}Service.selectList(entity));
    }

   /**
    * ${className} 根据实体对象查询分页列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/page")
    public CommonResp<PageBean<${className}>> selectPage(@RequestBody ${className} entity) {
        return RespEntity.ok(${objectName}Service.selectPage(entity));
    }

    /**
    * ${className} 新增或者修改记录，根据主键判断，主键为空则新增，否则修改。
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/obj")
    public CommonResp<Object> save(@RequestBody ${className} ${objectName}) {
        ${objectName}Service.save(${objectName});
        return RespEntity.ok();
    }

    /**
    * ${className} 根据主键删除数据
    * @return
    * @throws Exception
    */
    @DeleteMapping(value = "/obj")
    public CommonResp<Object> deleteById(@RequestParam(value="${IdColEntity.fieldJavaName}") ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) {
        if(Objects.isNull(${IdColEntity.fieldJavaName})){
           throw new BusiException("删除主键不可为空") ;
        }
        ${objectName}Service.deleteById(${IdColEntity.fieldJavaName});
        return RespEntity.ok();
    }

}
