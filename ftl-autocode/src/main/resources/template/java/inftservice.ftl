package ${inftServicePackagePath};

import ${entityPackagePath}.${className};

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
}
