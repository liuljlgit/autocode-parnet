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
public class DailyAmountExcelEntity extends ExcelErrorResp {

    @Excel(name = "主键",orderNum = "1",isImportField = "true",replace = "_null")
    private Long daId;

    @Excel(name = "日期",orderNum = "2",isImportField = "true",replace = "_null",importFormat = DatePatternConst.NORM_DATE_PATTERN)
    @NotNull(message = "日期不能为空")
    private Date dateTime;

    @Excel(name = "名称",orderNum = "3",isImportField = "true",replace = "_null")
    private String name;

    public static class Verify implements IExcelVerifyHandler<DailyAmountExcelEntity> {
        @Override
        public ExcelVerifyHandlerResult verifyHandler(DailyAmountExcelEntity dailyAmountExcelEntity) {
            ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult();
            result.setSuccess(true);
            Long daId = dailyAmountExcelEntity.getDaId();
            if(daId.equals(1L)){
                result.setSuccess(false);
                result.setMsg("daId不能为1");
            }
            return result;
        }
    }

    public static class DataHandler implements IExcelDataHandler<DailyAmountExcelEntity> {

        @Override
        public Object exportHandler(DailyAmountExcelEntity dailyAmountExcelEntity, String s, Object o) {
            if(s.equals("主键") && o.equals(1L)){
                return 1000L;
            } else {
                return dailyAmountExcelEntity.getDaId();
            }
        }

        @Override
        public String[] getNeedHandlerFields() {
            String[] strings = new String[1];
            strings[0] = "主键";
            return strings;
        }

        @Override
        public Object importHandler(DailyAmountExcelEntity dailyAmountExcelEntity, String s, Object o) {
            return null;
        }

        @Override
        public void setNeedHandlerFields(String[] strings) {

        }

        @Override
        public void setMapValue(Map<String, Object> map, String s, Object o) {

        }

        @Override
        public Hyperlink getHyperlink(CreationHelper creationHelper, DailyAmountExcelEntity dailyAmountExcelEntity, String s, Object o) {
            return null;
        }
    }
}
