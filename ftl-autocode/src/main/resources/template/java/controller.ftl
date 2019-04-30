package ${ctrlPackagePath};

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.utils.BeanUtil;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import ${inftServicePackagePath}.I${className}Service;
import ${entityPackagePath}.${className};
import ${respPackagePath}.${className}Resp;
import ${reqPackagePath}.${className}Req;
import ${queryEntityPackagePath}.${className}Query;
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
    public CommonResp<${className}Resp> load${className}ByKey(@PathVariable(value="${IdColEntity.fieldJavaName}") ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws Exception {
        if(Objects.isNull(${IdColEntity.fieldJavaName})){
         throw new BusiException("请输入要获取的数据的ID") ;
        }
        ${className} ${objectName} = ${objectName}Service.load${className}ByKey(${IdColEntity.fieldJavaName});
        return RespEntity.ok(new ${className}Resp(${objectName}));
    }

   /**
    * ${className} 根据实体对象查询列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/${className?lower_case}/list")
    public CommonResp<PageBean<${className}Resp>> get${className}PageList(@RequestBody ${className}Req ${objectName}Req) throws Exception {
        ${className}Query query = BeanUtil.createBean(${objectName}Req, ${className}Query.class);
        PageBean<${className}Resp> pageList = ${objectName}Service.get${className}PageList(query);
        return RespEntity.ok(pageList);
    }

    /**
    * ${className} 新增或者修改记录，根据主键判断，主键为空则新增，否则修改。
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/${className?lower_case}")
    public CommonResp<Object> save${className}(@RequestBody ${className}Req ${objectName}Req) throws  Exception{
        ${className} ${objectName} = BeanUtil.createBean(${objectName}Req, ${className}.class);
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
