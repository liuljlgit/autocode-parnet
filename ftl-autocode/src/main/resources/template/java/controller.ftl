package ${ctrlPackagePath};

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import ${inftServicePackagePath}.I${className}Service;
import ${entityPackagePath}.${className};
import ${respPackagePath}.${className}Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

/**
 * ${className}Ctrl 控制层方法
 * @author lijun
 */
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
   @GetMapping(value = "/{${IdColEntity.fieldJavaName}}")
   public String load${className}ByKey(@PathVariable(value="${IdColEntity.fieldJavaName}") ${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws Exception {
      if(Objects.isNull(${IdColEntity.fieldJavaName})){
         throw new BusiException("请输入要获取的数据的ID") ;
      }
      ${className} ${objectName} = ${objectName}Service.load${className}ByKey(${IdColEntity.fieldJavaName});
      return RespEntity.ok(new ${className}Resp(${objectName}));
   }


	//------------------------ custom code begin ------------------------//
    ${customCode!""}
	//======================== custom code end ========================//
}
