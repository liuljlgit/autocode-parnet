package ${implServicePackagePath};

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;
import com.cloud.ftl.ftlbasic.service.AbstractBaseService;
import org.springframework.data.redis.core.RedisTemplate;
import ${entityPackagePath}.${className};
import ${inftServicePackagePath}.I${className}Service;
import ${daoPackagePath}.I${className}Dao;

/**
 * I${className}Service service实现类
 * @author lijun
 */
@Service("${objectName}Service")
public class ${className}ServiceImpl extends AbstractBaseService<${className}> implements I${className}Service {

    public ${className}ServiceImpl(I${className}Dao ${objectName}Dao,RedisTemplate<String,String> stringRedisTemplate){
        super(${objectName}Dao,stringRedisTemplate);
    }

    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;
    @Autowired
    private I${className}Dao ${objectName}Dao;

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
            ${objectName}.set${IdColEntity.fieldJavaName?cap_first}(selectMaxId());
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
                ${objectName}.set${IdColEntity.fieldJavaName?cap_first}(selectMaxId());
            }
        }
        ${objectName}Dao.batchAdd${className}(list);
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
            ${objectName}.set${IdColEntity.fieldJavaName?cap_first}(selectMaxId());
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
                e.set${IdColEntity.fieldJavaName?cap_first}(selectMaxId());
                return e;
            }).collect(Collectors.toList());
            batchAdd${className}(addList);
        }
        if(!CollectionUtils.isEmpty(updateList)){
            batchUpdate${className}(updateList,false);
        }
    }

}