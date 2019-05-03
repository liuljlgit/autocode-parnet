package ${queryEntityPackagePath};

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;

public class ${className}Query extends BaseQuery {
<#list tableColEntitys as col>

    /**
     * field comment:${col.comment}
     */
	private ${col.fieldJavaType} ${col.fieldJavaName};
</#list>

<#list tableColEntitys as col>

    public static final transient String TABLE_${col.field?upper_case} = "${col.field}";
</#list>

<#list tableColEntitys as col>

    public void set${col.fieldJavaName?cap_first}(${col.fieldJavaType} ${col.fieldJavaName}) {
        addCriteria(${className}Query.TABLE_${col.field?upper_case},${col.fieldJavaName});
    }

    public void set${col.fieldJavaName?cap_first}(Opt opt,${col.fieldJavaType} ${col.fieldJavaName}) throws Exception {
        addCriteria(${className}Query.TABLE_${col.field?upper_case},opt,${col.fieldJavaName});
    }

    public void set${col.fieldJavaName?cap_first}(Opt opt,List<${col.fieldJavaType}> ${col.fieldJavaName}List) throws Exception {
        addCriteria(${className}Query.TABLE_${col.field?upper_case},opt,${col.fieldJavaName}List);
    }

    public void set${col.fieldJavaName?cap_first}(Opt opt,${col.fieldJavaType} ${col.fieldJavaName}1,${col.fieldJavaType} ${col.fieldJavaName}2) throws Exception {
        addCriteria(${className}Query.TABLE_${col.field?upper_case},opt,${col.fieldJavaName}1,${col.fieldJavaName}2);
    }
</#list>

}