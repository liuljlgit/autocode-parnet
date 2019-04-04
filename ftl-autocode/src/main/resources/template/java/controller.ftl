package ${ctrlPackagePath};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import ${inftServicePackagePath}.I${className}Service;

/**
 * ${className}Ctrl 控制层方法
 * @author lijun
 */
@RestController
@RequestMapping("/${className?lower_case}")
public class ${className}Ctrl{

	@Autowired
    private I${className}Service ${objectName}Service;


	//------------------------ custom code begin ------------------------//
    ${customCode!""}
	//======================== custom code end ========================//
}
