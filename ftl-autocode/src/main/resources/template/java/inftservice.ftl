package ${inftServicePackagePath};

import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import ${entityPackagePath}.${className};
import ${queryEntityPackagePath}.${className}Query;

/**
 * I${className}Service service接口类
 * @author lijun
 */
public interface I${className}Service {

    /**
     * 根据主键获取对象
     * @param ${IdColEntity.fieldJavaName}
     * @return
     * @throws Exception
     */
    ${className} load${className}ByKey(${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws Exception;

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<${className}> get${className}PageList(${className}Query query) throws Exception;

    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
     List<${className}> find${className}List(${className}Query query) throws Exception;
}
