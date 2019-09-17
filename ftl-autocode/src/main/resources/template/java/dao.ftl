package ${daoPackagePath};

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import ${entityPackagePath}.${className};

/**
  * 接口类 I${className}Dao
  * @author lijun
  */
@Repository
public interface I${className}Dao extends IBaseMapper<${className}>{

    //------------------------ custom code begin ------------------------//
    ${customCode!""}
    //======================== custom code end ========================//

}