package com.cloud.ftl.ftltest.test.constant;

import com.cloud.ftl.ftlbasic.utils.HumpUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * DailyAmountTable类
 */
public class DailyAmountTable {

    public static Map<String,String> map;

    static {
        map = new HashMap<>();
        Class<DailyAmountTable> tClass = DailyAmountTable.class;
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if(!"map".equals(fieldName)){
                map.put(fieldName,HumpUtil.convertToJava(fieldName));
            }
        }
    }

    public static final transient String DA_ID = "da_id";

    public static final transient String ENTITY_ID = "entity_id";

    public static final transient String DATE_TIME = "date_time";

    public static final transient String SETT_PROFIT = "sett_profit";

    public static final transient String EXPEND_PROFIT = "expend_profit";

    public static final transient String INCOME_PROFIT = "income_profit";

    public static final transient String PROFIT_HOURS = "profit_hours";

    public static final transient String DEFICIT_HOURS = "deficit_hours";

    public static final transient String MINUS_DEVIATION = "minus_deviation";

    public static final transient String POSITIVE_DEVIATION = "positive_deviation";

    public static final transient String STATUS = "status";

    public static final transient String CE = "ce";

    public static final transient String SA = "sa";

    public static final transient String CREATE_TIME = "create_time";

    public static final transient String STATUS_TIME = "status_time";

}
