package com.cloud.ftl.ftlbasic.excel;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

/**
 * @author Liulj
 * @version v 0.1 2019/9/24 18:38
 */
@Slf4j
public class DefaultExportStyleHandler implements IExcelExportStyler {

    /**
     * 大标题样式
     */
    private CellStyle headerStyle;

    /**
     * 每列标题样式
     */
    private CellStyle titleStyle;

    /**
     * 数据行样式
     */
    private CellStyle styles;

    public DefaultExportStyleHandler(Workbook workbook) {
        this.init(workbook);
    }

    /**
     * 初始化样式
     *
     * @param workbook
     */
    private void init(Workbook workbook) {
        CustomCellStyle customCellStyle = new CustomCellStyle();
        this.headerStyle = customCellStyle.initHeaderStyle(workbook);
        this.titleStyle = customCellStyle.initTitleStyle(workbook);
        this.styles = customCellStyle.initStyles(workbook);
    }

    /**
     * 大标题样式
     *
     * @param color
     * @return
     */
    @Override
    public CellStyle getHeaderStyle(short color) {
        return headerStyle;
    }

    /**
     * 每列标题样式
     *
     * @param color
     * @return
     */
    @Override
    public CellStyle getTitleStyle(short color) {
        return titleStyle;
    }

    /**
     * 数据行样式
     *
     * @param parity 可以用来表示奇偶行
     * @param entity 数据内容
     * @return 样式
     */
    @Override
    public CellStyle getStyles(boolean parity, ExcelExportEntity entity) {
        return styles;
    }

    /**
     * 获取样式方法
     *
     * @param dataRow 数据行
     * @param obj     对象
     * @param data    数据
     */
    @Override
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data) {
        return getStyles(true, entity);
    }

    /**
     * 模板使用的样式设置
     */
    @Override
    public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams) {
        return null;
    }

}
