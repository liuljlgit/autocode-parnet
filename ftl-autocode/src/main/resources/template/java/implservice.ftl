package ${implServicePackagePath};

import org.springframework.stereotype.Service;
import ${entityPackagePath}.${className};
import ${inftServicePackagePath}.I${className}Service;

/**
 * I${className}Service service实现类
 * @author lijun
 */
@Service("${objectName}Service")
public class ${className}ServiceImpl implements I${className}Service {

    /**
     * 根据主键获取对象
     * @param ${IdColEntity.fieldJavaName}
     * @return
     * @throws Exception
     */
    @Override
    public ${className} load${className}ByKey(${IdColEntity.fieldJavaType} ${IdColEntity.fieldJavaName}) throws Exception {
        return null;
    }
}