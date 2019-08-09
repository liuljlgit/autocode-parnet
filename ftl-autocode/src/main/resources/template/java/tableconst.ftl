package ${tableConstPath};

import com.cloud.ftl.ftlbasic.utils.HumpUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * ${className}Table类
 */
public class ${className}Table {

    public static Map<String,String> map;

    static {
        map = new HashMap<>();
        Class<${className}Table> tClass = ${className}Table.class;
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if(!"map".equals(fieldName)){
                map.put(fieldName,HumpUtil.convertToJava(fieldName));
            }
        }
    }
<#list tableColEntitys as col>

    public static final transient String ${col.field?upper_case} = "${col.field}";
</#list>

}
