package ${entityPackagePath};

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;
import lombok.Data;
import com.cloud.ftl.ftlbasic.aspect.PrimaryKey;

@Data
public class ${className} extends BaseQuery {
<#list tableColEntitys as col>

    /**
     * field comment:${col.comment}
     */
    <#if col.fieldJavaName == IdColEntity.fieldJavaName>
    @PrimaryKey
    private ${col.fieldJavaType} ${col.fieldJavaName};
    <#else>
    private ${col.fieldJavaType} ${col.fieldJavaName};
    </#if>
</#list>
<#list tableColEntitys as col>

    public static final transient String ${col.field?upper_case} = "${col.field}";
</#list>

<#list tableColEntitys as col>

    public void and${col.fieldJavaName?cap_first}(Opt opt,${col.fieldJavaType} ${col.fieldJavaName}) throws Exception {
        addCriteria(${col.field?upper_case},opt,${col.fieldJavaName});
    }

    public void and${col.fieldJavaName?cap_first}(Opt opt,List<${col.fieldJavaType}> ${col.fieldJavaName}List) throws Exception {
        addCriteria(${col.field?upper_case},opt,${col.fieldJavaName}List);
    }

    public void and${col.fieldJavaName?cap_first}(Opt opt,${col.fieldJavaType} ${col.fieldJavaName}1,${col.fieldJavaType} ${col.fieldJavaName}2) throws Exception {
        addCriteria(${col.field?upper_case},opt,${col.fieldJavaName}1,${col.fieldJavaName}2);
    }
</#list>

}