package ${daoPackagePath};

import org.springframework.stereotype.Repository;
import java.util.List;
import ${entityPackagePath}.${className};
import ${queryEntityPackagePath}.${className}Query;

/**
  * 接口类 I${className}Dao
  * @author lijun
  */
@Repository
public interface I${className}Dao {

    /**
     * 获取表的最大ID
     * @return
     */
     Long selectMax${className}Id();

    /**
     * 根据主键获取对象
     * @param ${IdColEntity.fieldJavaName}
     * @return
     */
    ${className} load${className}ByKey(${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName});

    /**
     * 查询列表
     * @param query
     * @return
     */
    List<${className}> find${className}List(${className}Query query);

    /**
     * 获取查询总数
     * @param query
     * @return
     */
    Long getTotal${className}(${className}Query query);
}