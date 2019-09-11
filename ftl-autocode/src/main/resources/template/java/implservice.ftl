package ${implServicePackagePath};

import org.springframework.stereotype.Service;
import ${implCachePackagePath}.${className}CacheImpl;
import ${inftServicePackagePath}.I${className}Service;

/**
 * I${className}Service service实现类
 * @author lijun
 */
@Service("${objectName}Service")
public class ${className}ServiceImpl extends ${className}CacheImpl implements I${className}Service {


}