package ${respPackagePath};

import com.cloud.ftl.ftlbasic.utils.CommonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ${entityPackagePath}.${className};

import java.util.Date;

/**
 * ${className}Resp 返回实体类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${className}Resp extends ${className}{

	public ${className}Resp(${className} ${objectName}){
		CommonUtil.copyPropertiesIgnoreNull(${objectName},this);
	}

	<#list tableColEntitys as col>
	<#if (col.fieldJavaType = "Date")>

	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date get${col.fieldJavaName?cap_first}() {
		return super.get${col.fieldJavaName?cap_first}();
	}
	</#if>
	</#list>
}
