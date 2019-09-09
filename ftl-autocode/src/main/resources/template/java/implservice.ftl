package ${implServicePackagePath};

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.Collection;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import com.cloud.ftl.ftlbasic.annotation.UseCache;
import ${entityPackagePath}.${className};
import ${inftServicePackagePath}.I${className}Service;
import ${daoPackagePath}.I${className}Dao;

/**
 * I${className}Service service实现类
 * @author lijun
 */
@Service("${objectName}Service")
@UseCache
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements I${className}Service {

    @Override
    public ${className} selectById(Serializable id, String... nullErrMsg) {
        return super.selectById(id, nullErrMsg);
    }

    @Override
    public ${className} selectOne(${className} query, String... nullErrMsg) {
        return super.selectOne(query, nullErrMsg);
    }

    @Override
    public List<${className}> selectList(${className} query, String... emptyErrMsg) {
        return super.selectList(query, emptyErrMsg);
    }

    @Override
    public List<${className}> selectList(${className} query, List<String> fieldList, String... emptyErrMsg) {
        return super.selectList(query, fieldList, emptyErrMsg);
    }

    @Override
    public List<${className}> selectBatchIds(Collection<? extends Serializable> list, String... emptyErrMsg) {
        return super.selectBatchIds(list, emptyErrMsg);
    }

    @Override
    public Long selectCount(${className} query) {
        return super.selectCount(query);
    }

    @Override
    public int update(${className} entity, Update... args) {
        return super.update(entity, args);
    }

    @Override
    public int updateByMap(${className} oEntity, FuncMap funcMap) {
        return super.updateByMap(oEntity, funcMap);
    }

    @Override
    public void updateBatch(List<${className}> list, Update... args) {
        super.updateBatch(list, args);
    }

    @Override
    public int add(${className} entity) {
        return super.add(entity);
    }

    @Override
    public void addBatch(List<${className}> list) {
        super.addBatch(list);
    }

    @Override
    public void delete(${className} entity) {
        super.delete(entity);
    }

    @Override
    public int deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    public void deleteBatchIds(Collection<? extends Serializable> list) {
        super.deleteBatchIds(list);
    }

    //---------------- 自定义方法请写在下面 ----------------------------------------------------------------

}