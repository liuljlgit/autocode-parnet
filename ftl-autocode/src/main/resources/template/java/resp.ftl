package ${respPackagePath};

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.cloud.ftl.ftlbasic.utils.CommonUtil;
import ${entityPackagePath}.${className};

import java.util.Date;

@JSONType(includes = {
	<#list tableColEntitys as col>
	${className}.PROP_${col.field?upper_case}<#if col_index < (tableColEntitys?size - 1)>,</#if>
	</#list>
})

/**
 * ${className}Req 返回实体类
 */
public class ${className}Resp extends ${className}{

	public ${className}Resp(${className} ${objectName}){
		CommonUtil.copyPropertiesIgnoreNull(${objectName},this);
	}

	<#list tableColEntitys as col>
	<#if (col.fieldJavaType = "Date")>

	@Override
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date get${col.fieldJavaName?cap_first}() {
		return super.get${col.fieldJavaName?cap_first}();
	}
	</#if>
	</#list>
}
