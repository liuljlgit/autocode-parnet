package ${daoPackagePath};

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import ${entityPackagePath}.${className};

/**
  * 接口类 I${className}Dao
  * @author lijun
  */
@Repository
public interface I${className}Dao extends IBaseMapper<${className}>{

    /**
     * 新增对象
     * @param ${objectName}
     * @return
     */
    Integer add${className}(${className} ${objectName});

    /**
     * 批量新增对象
     * @param list
     */
    void batchAdd${className}(List<${className}> list);

    /**
     * 批量更新对象
     * @param list
     */
    void batchUpdate${className}(List<${className}> list);

    /**
     * 更新对象（全更新）
     * @param ${objectName}
     * @return
     */
    Integer fullUpdate${className}(${className} ${objectName});

    /**
     * 批量更新对象（全更新）
     * @param list
     */
    void batchFullUpdate${className}(List<${className}> list);

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