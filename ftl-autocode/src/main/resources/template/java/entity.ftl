package ${entityPackagePath};

import java.util.Date;
import java.math.BigDecimal;

public class ${className} {
<#list tableColEntitys as col>

    /**
     * field comment:${col.comment}
     */
	private ${col.fieldJavaType} ${col.fieldJavaName};
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