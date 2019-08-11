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

    public static final transient String ${col.field?upper_case} = "${col.field}";
</#list>

    public static Map<String,Integer> map;

    static {
        map = new HashMap<>();
        Class<${className}> aClass = ${className}.class;
        Field[] fields = aClass.getDeclaredFields();
        Integer index = 0;
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            //过滤调不是只有私有属性修饰符的 1+8+16+128
            if(modifiers != 153){
                continue;
            }
            field.setAccessible(true);
            try {
                String fieldVal = (String)field.get(aClass);
                map.putIfAbsent(fieldVal,index++);
            } catch (IllegalAccessException e) {

            }
        }
    }

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