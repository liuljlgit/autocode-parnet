package com.cloud.ftl.ftltest.test.constant;

import com.cloud.ftl.ftlbasic.utils.HumpUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * LoadTimeTable类
 */
public class LoadTimeTable {

    public static Map<String,String> map;

    static {
        map = new HashMap<>();
        Class<LoadTimeTable> tClass = LoadTimeTable.class;
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if(!"map".equals(fieldName)){
                map.put(fieldName,HumpUtil.convertToJava(fieldName));
            }
        }
    }

    public static final transient String LT_ID = "lt_id";

    public static final transient String ENTITY_ID = "entity_id";

    public static final transient String START_TIME = "start_time";

    public static final transient String END_TIME = "end_time";

    public static final transient String STATUS = "status";

    public static final transient String CREATE_TIME = "create_time";

    public static final transient String STATUS_TIME = "status_time";

}
