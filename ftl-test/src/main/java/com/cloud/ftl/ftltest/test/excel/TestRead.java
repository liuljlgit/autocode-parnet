package com.cloud.ftl.ftltest.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import java.util.Date;

/**
 * @author Liulj
 * @version v 0.1 2019/9/26 16:03
 */
@Data
public class TestRead {

    @ExcelProperty("主键")
    private Long daId;

    @ExcelProperty("日期")
    private Date dateTime;

    @ExcelProperty("名称")
    private String name;

}
