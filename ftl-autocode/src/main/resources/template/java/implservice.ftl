package ${implServicePackagePath};

import org.springframework.stereotype.Service;
import com.cloud.ftl.ftlbasic.service.BaseServiceImpl;
import ${entityPackagePath}.${className};
import ${inftServicePackagePath}.I${className}Service;
import ${daoPackagePath}.I${className}Dao;

/**
 * I${className}Service service实现类
 * @author lijun
 */
@Service("${objectName}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements I${className}Service {


}