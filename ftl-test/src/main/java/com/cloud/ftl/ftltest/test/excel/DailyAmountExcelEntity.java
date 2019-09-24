package com.cloud.ftl.ftltest.test.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.cloud.ftl.ftlbasic.constant.DatePatternConst;
import com.cloud.ftl.ftlbasic.webEntity.ExcelErrorResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Liulj
 * @version v 0.1 2019/9/23 10:36
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyAmountExcelEntity extends ExcelErrorResp {

    @Excel(name = "主键",orderNum = "1",isImportField = "true",replace = "_null")
    private Long daId;

    @Excel(name = "日期",orderNum = "2",isImportField = "true",replace = "_null",importFormat = DatePatternConst.NORM_DATE_PATTERN)
    @NotNull(message = "日期不能为空")
    private Date dateTime;

    @Excel(name = "名称",orderNum = "3",isImportField = "true",replace = "_null")
    private String name;

}
