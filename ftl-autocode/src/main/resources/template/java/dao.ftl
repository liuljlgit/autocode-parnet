package ${daoPackagePath};

import org.springframework.stereotype.Repository;
import ${entityPackagePath}.${className};

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
}