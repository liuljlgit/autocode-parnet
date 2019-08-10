package ${ctrlPackagePath};

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.utils.BeanUtil;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import ${inftServicePackagePath}.I${className}Service;
import ${entityPackagePath}.${className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

/**
 * ${className}Ctrl 控制层方法
 * @author lijun
 */
@RestController
public class ${className}Ctrl{

  @Autowired
  private I${className}Service ${objectName}Service;


    /**
    * ${className} 根据主键获取单个数据
    * @return
    * @throws Exception
    */
    @GetMapping(value = "/${className?lower_case}/{${IdColEntity.fieldJavaName}}")
    public CommonResp<${className}> load${className}ByKey(@PathVariable(value="${IdColEntity.fieldJavaName}") ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws Exception {
        if(Objects.isNull(${IdColEntity.fieldJavaName})){
         throw new BusiException("请输入要获取的数据的ID") ;
        }
        ${className} ${objectName} = ${objectName}Service.load${className}ByKey(${IdColEntity.fieldJavaName});
        return RespEntity.ok(${objectName});
    }

   /**
    * ${className} 根据实体对象查询列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/${className?lower_case}/list")
    public CommonResp<PageBean<${className}>> get${className}PageList(@RequestBody ${className} query) throws Exception {
        PageBean<${className}> pageList = ${objectName}Service.get${className}PageList(query);
        return RespEntity.ok(pageList);
    }

    /**
    * ${className} 新增或者修改记录，根据主键判断，主键为空则新增，否则修改。
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/${className?lower_case}")
    public CommonResp<Object> save${className}(@RequestBody ${className} ${objectName}) throws  Exception{
        ${objectName}Service.save${className}(${objectName});
        return RespEntity.ok();
    }

    /**
    * ${className} 根据主键删除数据
    * @return
    * @throws Exception
    */
    @DeleteMapping(value = "/${className?lower_case}/{${IdColEntity.fieldJavaName}}")
    public CommonResp<Object> delete${className}(@PathVariable(value="${IdColEntity.fieldJavaName}") ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws  Exception{
        if(Objects.isNull(${IdColEntity.fieldJavaName})){
           throw new BusiException("删除主键不可为空") ;
        }
        ${objectName}Service.delete${className}(${IdColEntity.fieldJavaName});
        return RespEntity.ok();
    }

}
