package ${entityPackagePath};

import java.util.Date;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BasePage;
import java.io.Serializable;

public class ${className} extends BasePage implements Serializable {
<#list tableColEntitys as col>

    /**
     * field comment:${col.comment}
     */
	private ${col.fieldJavaType} ${col.fieldJavaName};
</#list>

<#list tableColEntitys as col>

    public static final transient String PROP_${col.field?upper_case} = "${col.fieldJavaName}";
</#list>

<#list tableColEntitys as col>

    public ${col.fieldJavaType} get${col.fieldJavaName?cap_first}() {
        return ${col.fieldJavaName};
    }

    public void set${col.fieldJavaName?cap_first}(${col.fieldJavaType} ${col.fieldJavaName}) {
        this.${col.fieldJavaName} = ${col.fieldJavaName};
    }
</#list>

}