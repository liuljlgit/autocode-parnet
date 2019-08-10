package ${inftServicePackagePath};

import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.service.IBaseService;
import ${entityPackagePath}.${className};

/**
 * I${className}Service service接口类
 * @author lijun
 */
public interface I${className}Service extends IBaseService<${className}>{

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

}
