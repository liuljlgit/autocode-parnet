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
     * 更新对象
     * @param ${objectName}
     * @return
     */
    Integer update${className}(${className} ${objectName});

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

   /**
    * 根据ID列表从数据库中查询列表
    * @param list
    * @return
    */
    List<${className}> find${className}ByIdList(List<${IdColEntity.fieldJavaType}> list);
}