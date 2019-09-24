package com.cloud.ftl.ftlbasic.excel;

import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Liulj
 * @version v 0.1 2019/9/23 16:49
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ExcelErrorResp implements IExcelModel,IExcelDataModel {

    private String errorMsg;

    private int rowNum;

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public int getRowNum() {
        return rowNum;
    }

    @Override
    public void setRowNum(int rowNum){
        this.rowNum = rowNum;
    }

}
