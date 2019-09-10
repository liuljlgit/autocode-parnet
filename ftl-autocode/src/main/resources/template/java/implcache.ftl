package ${implCachePackagePath};

import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import ${entityPackagePath}.${className};
import ${inftServicePackagePath}.I${className}Service;
import ${inftCachePackagePath}.I${className}Cache;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * I${className}Cache cache实现类
 * @author lijun
 */
@Service("${objectName}Cache")
public class ${className}CacheImpl extends BaseServiceImpl<${className}> implements I${className}Cache {

    @Override
    public Long selectMaxId() {
        return super.selectMaxId();
    }

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
    public void addBatch(List<${className}> list, int batchSize) {
        super.addBatch(list, batchSize);
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

    @Override
    public void save(${className} ${objectName}, Update... args) {
        super.save(${objectName}, args);
    }

    @Override
    public void saveBatch(List<${className}> list, Update... args) {
        super.saveBatch(list, args);
    }

}