package ${inftServicePackagePath};

import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import ${entityPackagePath}.${className};
import ${queryEntityPackagePath}.${className}Query;
import ${respPackagePath}.${className}Resp;

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
     * 普通查询获取单个结果
     * @param query
     * @return
     * @throws Exception
     */
    ${className} selectOne${className}(${className}Query query) throws Exception;

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    PageBean<${className}Resp> get${className}PageList(${className}Query query) throws Exception;

    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
     List<${className}> find${className}List(${className}Query query) throws Exception;

    /**
     * 新增对象
     * @param ${objectName}
     * @return
     * @throws Exception
     */
    Integer add${className}(${className} ${objectName}) throws Exception;

    /**
     * 批量新增对象
     * @param list
     * @throws Exception
     */
    void batchAdd${className}(List<${className}> list) throws Exception;

    /**
     * 更新对象
     * @param ${objectName}
     * @param fullUpdate
     * @return
     * @throws Exception
     */
    Integer update${className}(${className} ${objectName},Boolean fullUpdate) throws Exception;

    /**
     * 批量更新
     * @param list
     * @param fullUpdate
     * @throws Exception
     */
    void batchUpdate${className}(List<${className}> list,Boolean fullUpdate) throws Exception;

    /**
     * 删除对象
     * @param ${IdColEntity.fieldJavaName}
     * @return
     * @throws Exception
     */
    Integer delete${className}(${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws Exception;

    /**
     * 批量删除对象
     * @param list
     * @throws Exception
     */
    void batchDelete${className}(List<${IdColEntity.fieldJavaType}> list) throws Exception;

    /**
     * 根据ID列表从数据库中查询列表
     * @param list
     * @return
     * @throws Exception
     */
    List<${className}> find${className}ByIdList(List<${IdColEntity.fieldJavaType}> list) throws Exception;

    /**
     * 保存记录
     * @param ${objectName}
     * @throws Exception
     */
     void save${className}(${className} ${objectName}) throws Exception;

    /**
     * 批量保存记录
     * @param list
     * @throws Exception
     */
     void save${className}List(List<${className}> list) throws Exception;

    //------------------------ custom code begin ------------------------//
    ${customCode!""}
    //======================== custom code end ========================//

}
