package ${implCachePackagePath};

import com.alibaba.fastjson.parser.ParserConfig;
import com.cloud.ftl.ftlbasic.enums.Update;
import com.cloud.ftl.ftlbasic.func.FuncMap;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ${entityPackagePath}.${className};
import ${inftServicePackagePath}.I${className}Service;
import ${inftCachePackagePath}.I${className}Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * I${className}Cache cache实现类
 * @author lijun
 */
@Slf4j
@Service("${objectName}Cache")
public class ${className}CacheImpl extends BaseServiceImpl<${className}> implements I${className}Cache {

    private final static String CLS_NAME = ${className}.class.getSimpleName();
    private final static String PAGE_IDS_KEY = "PAGE:".concat(CLS_NAME).concat(":").concat("IDS");
    private final static String PAGE_TOTAL_KEY = "PAGE:".concat(CLS_NAME).concat(":").concat("TOTAL");
    private final static String CUSTOM_QUERY_KEY = "CUSTOM:QUERY:".concat(CLS_NAME);

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Long selectMaxId() {
        return super.selectMaxId();
    }

    @Override
    public ${className} selectById(Serializable id, String... nullErrMsg) {
        String entityKey = CLS_NAME.concat(":").concat(String.valueOf(id));
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        ${className} redisVal = (${className})redisTemplate.opsForValue().get(entityKey);
        if(Objects.nonNull(redisVal)){
            log.info(" --------- Get Entity From Cache --------- ");
            return redisVal;
        }
        log.info(" --------- Get Entity From DB --------- ");
        ${className} dbVal = super.selectById(id, nullErrMsg);
        if(Objects.nonNull(dbVal)){
            redisTemplate.opsForValue().set(entityKey,dbVal);
        }
        return dbVal;
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