package ${implServicePackagePath};

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;
import ${entityPackagePath}.${className};
import ${inftServicePackagePath}.I${className}Service;
import ${daoPackagePath}.I${className}Dao;
import ${queryEntityPackagePath}.${className}Query;
import ${inftRedisPackagePath}.I${className}Redis;
import ${respPackagePath}.${className}Resp;

/**
 * I${className}Service service实现类
 * @author lijun
 */
@Service("${objectName}Service")
public class ${className}ServiceImpl implements I${className}Service {

    private static final Logger logger = LoggerFactory.getLogger(${className}ServiceImpl.class);

    @Autowired
    private I${className}Dao ${objectName}Dao;
    @Autowired
    private I${className}Redis ${objectName}Redis;

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
     * 普通查询获取单个结果
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public ${className} selectOne${className}(${className}Query query) throws Exception {
        List<${className}> list = find${className}List(query);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<${className}Resp> get${className}PageList(${className}Query query) throws Exception {
        if(Objects.isNull(query.getPage()) || Objects.isNull(query.getPageSize())){
            throw new BusiException("page and pageSize can not be null");
        }
        Long total = ${objectName}Dao.getTotal${className}(query);
        Long totalPage = (long)Math.ceil((double)total / query.getPageSize());
        List<${className}Resp> ${objectName}List = find${className}List(query).stream().map(${className}Resp::new).collect(Collectors.toList());
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

    /**
     * 新增对象
     * @param ${objectName}
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add${className}(${className} ${objectName}) throws Exception {
        if(Objects.isNull(${objectName})){
            return 0;
        }
        if(Objects.isNull(${objectName}.get${IdColEntity.fieldJavaName?cap_first}())){
            ${objectName}.set${IdColEntity.fieldJavaName?cap_first}(${objectName}Redis.get${className}Id());
        }
        return ${objectName}Dao.add${className}(${objectName});
    }

    /**
     * 批量新增对象
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAdd${className}(List<${className}> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for (${className} ${objectName} : list) {
            if(Objects.isNull(${objectName}.get${IdColEntity.fieldJavaName?cap_first}())){
                ${objectName}.set${IdColEntity.fieldJavaName?cap_first}(${objectName}Redis.get${className}Id());
            }
        }
        ${objectName}Dao.batchAdd${className}(list);
    }

    /**
     * 更新对象
     * @param ${objectName}
     * @param fullUpdate
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update${className}(${className} ${objectName},Boolean fullUpdate) throws Exception {
        if(Objects.isNull(${objectName})){
            return 0;
        }
        if(Objects.isNull(${objectName}.get${IdColEntity.fieldJavaName?cap_first}())){
            throw new BusiException("主键不能为空");
        }
        Integer result;
        if(fullUpdate){
            result = ${objectName}Dao.fullUpdate${className}(${objectName});
        } else {
            result = ${objectName}Dao.update${className}(${objectName});
        }
        return result;
    }

    /**
     * 批量更新
     * @param list
     * @param fullUpdate
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdate${className}(List<${className}> list,Boolean fullUpdate) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for (${className} ${objectName} : list) {
            if(Objects.isNull(${objectName}.get${IdColEntity.fieldJavaName?cap_first}())){
                throw new BusiException("主键不能为空");
            }
        }
        if(fullUpdate){
            ${objectName}Dao.batchFullUpdate${className}(list);
        } else {
            ${objectName}Dao.batchUpdate${className}(list);
        }
    }

   /**
    * 批量更新
    * @param params
    * @param query
    * @throws Exception
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdate${className}ByQuery(Map<String,Object> params,${className}Query query) throws Exception {
        if(CollectionUtils.isEmpty(params)){
            throw new BusiException("params不能为空");
        }
        if(Objects.isNull(query)){
            throw new BusiException("query不能为空");
        }
        ${objectName}Dao.batchUpdate${className}ByQuery(params,query);
    }

    /**
     * 删除对象
     * @param ${IdColEntity.fieldJavaName}
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete${className}(${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws Exception {
        if(Objects.isNull(${IdColEntity.fieldJavaName})){
            throw new BusiException("主键不能为空");
        }
        return ${objectName}Dao.delete${className}(${IdColEntity.fieldJavaName});
    }

    /**
     * 批量删除对象
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete${className}(List<${IdColEntity.fieldJavaType}> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        ${objectName}Dao.batchDelete${className}(list);
    }

   /**
    * 批量删除对象
    * @param query
    * @throws Exception
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete${className}ByQuery(${className}Query query) throws Exception {
        if(Objects.isNull(query)){
            throw new BusiException("查询对象参数不能为空");
        }
        ${objectName}Dao.batchDelete${className}ByQuery(query);
    }

    /**
     * 根据ID列表从数据库中查询列表
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public List<${className}> find${className}ByIdList(List<${IdColEntity.fieldJavaType}> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return Collections.EMPTY_LIST;
        }
        return ${objectName}Dao.find${className}ByIdList(list);
    }

    /**
     * 保存记录
     * @param ${objectName}
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save${className}(${className} ${objectName}) throws Exception {
        if(Objects.isNull(${objectName})){
           return ;
        }
        if(Objects.isNull(${objectName}.get${IdColEntity.fieldJavaName?cap_first}())){
            ${objectName}.set${IdColEntity.fieldJavaName?cap_first}(${objectName}Redis.get${className}Id());
            add${className}(${objectName});
        }else{
            update${className}(${objectName},false);
        }
    }

    /**
     * 批量保存记录
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save${className}List(List<${className}> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        List<${className}> addList = list.stream().filter(e -> Objects.isNull(e.get${IdColEntity.fieldJavaName?cap_first}())).collect(Collectors.toList());
        List<${className}> updateList = list.stream().filter(e -> Objects.nonNull(e.get${IdColEntity.fieldJavaName?cap_first}())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(addList)){
            addList = addList.stream().map(e->{
                e.set${IdColEntity.fieldJavaName?cap_first}(${objectName}Redis.get${className}Id());
                return e;
            }).collect(Collectors.toList());
            batchAdd${className}(addList);
        }
        if(!CollectionUtils.isEmpty(updateList)){
            batchUpdate${className}(updateList,false);
        }
    }

}