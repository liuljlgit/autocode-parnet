package ${entityPackagePath};

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cloud.ftl.ftlbasic.aspect.PrimaryKey;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${className} extends BaseQuery {
<#list tableColEntitys as col>

    /**
     * field comment:${col.comment}
     */
    <#if col.fieldJavaName == IdColEntity.fieldJavaName>
    @PrimaryKey
    private ${col.fieldJavaType} ${col.fieldJavaName};
    <#elseif col.fieldJavaType == 'Date'>
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private ${col.fieldJavaType} ${col.fieldJavaName};
    <#else>
    private ${col.fieldJavaType} ${col.fieldJavaName};
    </#if>
</#list>
<#list tableColEntitys as col>

    @JsonIgnore
    public static final transient String ${col.field?upper_case} = "${col.field}";
</#list>

<#list tableColEntitys as col>

    public void and${col.fieldJavaName?cap_first}(Opt opt) {
        addConditGroup(${col.field?upper_case},opt);
    }

    public void and${col.fieldJavaName?cap_first}(Opt opt,${col.fieldJavaType} ${col.fieldJavaName}) {
        addConditGroup(${col.field?upper_case},opt,${col.fieldJavaName});
    }

    public void and${col.fieldJavaName?cap_first}(Opt opt,List<${col.fieldJavaType}> list) {
        addConditGroup(${col.field?upper_case},opt,list);
    }

    public void and${col.fieldJavaName?cap_first}(Opt opt,${col.fieldJavaType} firstParam,${col.fieldJavaType} secondParam) {
        addConditGroup(${col.field?upper_case},opt,firstParam,secondParam);
    }
</#list>

}