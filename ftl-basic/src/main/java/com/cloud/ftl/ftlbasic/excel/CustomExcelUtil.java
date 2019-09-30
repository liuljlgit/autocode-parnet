package com.cloud.ftl.ftlbasic.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Liulj
 * @version v 0.1 2019/9/30 10:10
 */
@Slf4j
public class CustomExcelUtil {

    private static final String ERR_MSG_FORMAT_NO_COL = "excel解析失败,第%s行";
    private static final String ERR_MSG_FORMAT_NO_ROW = "excel解析失败,第%s列";
    private static final String ERR_MSG_FORMAT = "excel解析失败,第%s行%s列";

    private static String formatErrMsg(Integer rowIndex, Integer cellIndex){
        if(Objects.isNull(rowIndex) && Objects.nonNull(cellIndex)){
            return String.format(ERR_MSG_FORMAT_NO_ROW,cellIndex);
        }
        if(Objects.nonNull(rowIndex) && Objects.isNull(cellIndex)){
            return String.format(ERR_MSG_FORMAT_NO_COL,rowIndex);
        }
        return String.format(ERR_MSG_FORMAT,rowIndex,cellIndex);
    }

    /**
     * 得到row
     * @param sheet
     * @param rowIndex
     * @param errMsg
     * @return
     */
    public static Row getRow(Sheet sheet,Integer rowIndex,String... errMsg) {
        Row row = null;
        try {
            row = sheet.getRow(rowIndex);
        } catch (Exception e){
            if(errMsg.length > 0){
                throw new BusiException(errMsg[0]);
            }
        }
        return row;
    }

    /**
     * 创建新的行
     * @param sheet
     * @param rowIndex
     * @return
     */
    public static Row newRow(Sheet sheet,Integer rowIndex) {
        Row row;
        try {
            row = sheet.getRow(rowIndex);
        } catch (Exception e){
            row = sheet.createRow(rowIndex);
        }
        return row;
    }

    /**
     * 得到cell
     * @param sheet
     * @param rowIndex
     * @param cellIndex
     * @param errMsg
     * @return
     */
    public static Cell getCell(Sheet sheet,Integer rowIndex,Integer cellIndex,String... errMsg) {
        Cell cell = null;
        try {
            cell = sheet.getRow(rowIndex).getCell(cellIndex);
        } catch (Exception e){
            if(errMsg.length > 0){
                throw new BusiException(errMsg[0]);
            }
        }
        return cell;
    }

    /**
     * 得到cell
     * @param row
     * @param cellIndex
     * @param errMsg
     * @return
     */
    public static Cell getCell(Row row,Integer cellIndex,String... errMsg) {
        Cell cell = null;
        try {
            cell = row.getCell(cellIndex);
        } catch (Exception e){
            if(errMsg.length > 0){
                throw new BusiException(errMsg[0]);
            }
        }
        return cell;
    }

    /**
     * 创建新的cell
     * @param sheet
     * @param rowIndex
     * @param cellIndex
     * @return
     */
    public static Cell newCell(Sheet sheet,Integer rowIndex,Integer cellIndex) {
        Cell cell;
        try {
            cell = newRow(sheet,rowIndex).getCell(cellIndex);
        } catch (Exception e){
            cell = newRow(sheet,rowIndex).createCell(cellIndex);
        }
        return cell;
    }

    /**
     * 创建新的cell
     * @param row
     * @param cellIndex
     * @return
     */
    public static Cell newCell(Row row,Integer cellIndex) {
        Cell cell;
        try {
            cell = row.createCell(cellIndex);
        } catch (Exception e){
            throw new BusiException("row不能为空");
        }
        return cell;
    }

    /**
     * 得到单列的值
     * @param cell
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getCellValue(Cell cell, Class<T> cls,String... errMsg) {
        if(Objects.isNull(cell)) {
            if(errMsg.length > 0) {
                throw new BusiException(errMsg[0]);
            }
            return null;
        }
        //获取cell地址信息
        CellAddress address = cell.getAddress();
        int rowNum = address.getRow() + 1;
        int colNum = address.getColumn() + 1;
        //获取cellValue
        Object cellValue;
        switch (cell.getCellType()){
            case STRING:
                cellValue = cell.getRichStringCellValue().getString();
                if("".equals(cellValue)){
                    cellValue = null;
                }
                break;
            case NUMERIC:
                cell.setCellType(CellType.STRING);
                cellValue = cell.getRichStringCellValue().getString();
                if("".equals(cellValue)){
                    cellValue = null;
                }
                break;
            case BLANK:
                cellValue = null;
                break;
            default:
                throw new BusiException(formatErrMsg(rowNum,colNum).concat("不支持的导入类型"));
        }
        if(Objects.isNull(cellValue)){
            if(errMsg.length > 0){
                throw new BusiException(errMsg[0]);
            }
            return null;
        }
        try{
            return JSONObject.parseObject(JSON.toJSONString(cellValue),cls);
        } catch (Exception e){
            throw new BusiException(formatErrMsg(rowNum,colNum).concat("可能含有空格、字母、特殊字符,请检查"));
        }
    }

    /**
     * 设置cell的值
     * @param cell
     * @param cellVal
     */
    public static <T> void setCellValue(Cell cell, T cellVal , String... dateFormat) {
        if(cellVal instanceof Double){
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue((Double) cellVal);
        } else if(cellVal instanceof Boolean){
            cell.setCellType(CellType.BOOLEAN);
            cell.setCellValue((Boolean)cellVal);
        } else if(cellVal instanceof Date){
            if(dateFormat.length == 0){
                throw new BusiException("请设置正确的日期转换格式");
            }
            String formatVal = new SimpleDateFormat(dateFormat[0]).format(cellVal);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(formatVal);
        } else if(cellVal instanceof Calendar){
            if(dateFormat.length == 0){
                throw new BusiException("请设置正确的日期转换格式");
            }
            Date time = ((Calendar) cellVal).getTime();
            String formatVal = new SimpleDateFormat(dateFormat[0]).format(time);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(formatVal);
        } else {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(cellVal.toString());
        }

    }

    /**
     * 导入文件获取workbook，兼容xlx和xlsx
     * @param file
     * @return
     * @throws BusiException
     */
    public static Workbook getFileWorkbook(MultipartFile file) {
        Workbook workbook;
        try(InputStream in = file.getInputStream()){
            String fileSuffix = Objects.requireNonNull(getFileSuffix(Objects.requireNonNull(file.getOriginalFilename()))).toLowerCase();
            if (!"xlsx".equals(fileSuffix) && !"xls".equals(fileSuffix)) {
                throw new BusiException("文件格式不正确，请导入.xlsx或者.xls格式文件");
            }
            if ("xlsx".equals(fileSuffix)) {
                workbook = new XSSFWorkbook(in);
            } else {
                workbook = new HSSFWorkbook(in);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BusiException(e.getMessage());
        }
        return workbook;
    }

    private static String getFileSuffix(String fileName) {
        int index = fileName.lastIndexOf(46);
        return index != -1 && index != fileName.length() - 1 ? fileName.substring(index + 1) : null;
    }


    /**
     * 对列不确定性的表进行解析
     * @param workbook
     * @param sheetIndex
     * @param headRowIndex
     * @param headStartIndex
     * @param startRowIndex
     * @param endRowIndex
     * @param isAllowNull
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> Map<String, List<T>> parseCellsData(Workbook workbook,Integer sheetIndex, Integer headRowIndex,
                                                          Integer headStartIndex,Integer startRowIndex,Integer endRowIndex,
                                                          Boolean isAllowNull,Class<T> cls){
        Map<String, List<T>> mapDatas = Maps.newLinkedHashMap();
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row headRow = getRow(sheet,headRowIndex,"获取表头数据出错");
        int physicalNumberOfCells = headRow.getPhysicalNumberOfCells()-1;
        if(physicalNumberOfCells < headStartIndex){
            throw new BusiException("开始读取的列数不可大于实际可读的列数");
        }

        for(int i = headStartIndex; i <= physicalNumberOfCells; i++){
            List<T> listData = Lists.newArrayList();
            String headVal = getCellValue(getCell(sheet,headRowIndex,i), String.class,"表头数据获取失败");
            for(int j = startRowIndex;j <= endRowIndex;j++){
                T cellValue = getCellValue(getCell(sheet, j, i), cls);
                if(Objects.isNull(cellValue) && !isAllowNull) {
                    throw new BusiException(formatErrMsg(j,i).concat("数据存在空值"));
                }
                listData.add(cellValue);
            }
            mapDatas.putIfAbsent(headVal,listData);
        }
        return mapDatas;
    }

    /**
     * 把excel表数据解析成一个list
     * @param workbook
     * @param sheetIndex
     * @param listMaps
     * @param isAllowNull
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> parseRowsData(Workbook workbook,Integer sheetIndex,List<Integer[]> listMaps,Boolean isAllowNull,Class<T> cls) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        List<T> list = Lists.newArrayList();
        for (Integer[] listMap : listMaps) {
            if(listMap.length < 4){
                throw new BusiException("请设置正确的解析参数");
            }
            Integer startRowIndex = listMap[0];
            Integer endRowIndex = listMap[1];
            Integer startCellIndex = listMap[2];
            Integer endCellIndex = listMap[3];
            for(int i = startRowIndex;i <= endRowIndex;i++){
                for(int j = startCellIndex;j <= endCellIndex;j++){
                    T cellValue = getCellValue(getCell(sheet, i, j), cls);
                    if(Objects.isNull(cellValue) && !isAllowNull) {
                        throw new BusiException(formatErrMsg(i,j).concat("数据存在空值"));
                    }
                    list.add(cellValue);
                }
            }
        }
        return list;
    }

}
