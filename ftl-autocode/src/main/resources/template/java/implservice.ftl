package ${implServicePackagePath};

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import ${entityPackagePath}.${className};
import ${inftServicePackagePath}.I${className}Service;
import ${daoPackagePath}.I${className}Dao;
import ${queryEntityPackagePath}.${className}Query;

/**
 * I${className}Service service实现类
 * @author lijun
 */
@Service("${objectName}Service")
public class ${className}ServiceImpl implements I${className}Service {

    private static final Logger logger = LoggerFactory.getLogger(${className}ServiceImpl.class);

    @Autowired
    private I${className}Dao ${objectName}Dao;

    /**
     * 根据主键获取对象
     * @param ${IdColEntity.fieldJavaName}
     * @return
     * @throws Exception
     */
    @Override
    public ${className} load${className}ByKey(${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws Exception {
        if(Objects.isNull(${IdColEntity.fieldJavaName})){
            throw new BusiException("请输入要获取的数据的ID");
        }
        ${className} ${objectName} = ${objectName}Dao.load${className}ByKey(${IdColEntity.fieldJavaName});
        if(Objects.isNull(${objectName})){
            throw new BusiException("没有符合条件的记录！") ;
        }
        return ${objectName};
    }

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<${className}> get${className}PageList(${className}Query query) throws Exception {
        if(Objects.isNull(query.getPage()) || Objects.isNull(query.getPageSize())){
            throw new BusiException("page and pageSize can not be null");
        }
        Long total = ${objectName}Dao.getTotal${className}(query);
        Long totalPage = (long)Math.ceil((double)total / query.getPageSize());
        List<${className}> ${objectName}List = find${className}List(query);
        return new PageBean<>(totalPage,total,${objectName}List);
    }


    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<${className}> find${className}List(${className}Query query) throws Exception {
        if(Objects.isNull(query)){
            throw new BusiException("查询参数不能为空");
        }
        return ${objectName}Dao.find${className}List(query);
    }
}