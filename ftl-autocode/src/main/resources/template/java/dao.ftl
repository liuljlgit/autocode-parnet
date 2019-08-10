package ${daoPackagePath};

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import ${entityPackagePath}.${className};

/**
  * 接口类 I${className}Dao
  * @author lijun
  */
@Repository
public interface I${className}Dao extends IBaseMapper<${className}>{

    /**
     * 新增对象
     * @param entity
     * @return
     */
    Integer add${className}(@Param("at") ${className} entity);

    /**
     * 批量新增对象
     * @param list
     */
    void batchAdd${className}(List<${className}> list);

    /**
     * 删除对象
     * @param ${IdColEntity.fieldJavaName}
     * @return
     */
    Integer delete${className}(${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName});

    /**
     * 批量删除对象
     * @param list
     */
    void batchDelete${className}(List<Long> list);

    //------------------------ custom code begin ------------------------//
    ${customCode!""}
    //======================== custom code end ========================//

}