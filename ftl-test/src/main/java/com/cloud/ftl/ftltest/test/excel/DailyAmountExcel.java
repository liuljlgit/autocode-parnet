package com.cloud.ftl.ftltest.test.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelDataHandler;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.cloud.ftl.ftlbasic.constant.DatePatternConst;
import com.cloud.ftl.ftlbasic.excel.ExcelErrorResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * @author Liulj
 * @version v 0.1 2019/9/23 10:36
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyAmountExcel extends ExcelErrorResp {

    @Excel(name = "主键",orderNum = "1",isImportField = "true",replace = "_null")
    private Long daId;

    @Excel(name = "日期",orderNum = "2",isImportField = "true",replace = "_null",importFormat = DatePatternConst.NORM_DATE_PATTERN,exportFormat = DatePatternConst.EN_DATETIME_PATTERN)
    @NotNull(message = "日期不能为空")
    private Date dateTime;

    @Excel(name = "名称",orderNum = "3",isImportField = "true",replace = "_null")
    private String name;

}
