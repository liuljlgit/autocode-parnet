package com.cloud.ftl.ftlbasic.utils;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelBatchExportService;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import cn.afterturn.easypoi.excel.imports.sax.SaxReadExcel;
import cn.afterturn.easypoi.handler.inter.IReadHandler;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

@Slf4j
public class ExcelUtil {

    /**
     * Excel 导入
     *
     * @param in
     * @param pojoClass
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> importExcel(InputStream in, Class<?> pojoClass, ImportParams params) {
        try {
            return new ExcelImportService().importExcelByIs(in, pojoClass, params, false).getList();
        } catch (Exception e) {
            log.error("导入Excel数据失败", e);
            throw new BusiException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * Excel 通过SAX解析方法,适合大数据导入,不支持图片
     * 导入 数据源本地文件,不返回校验结果 导入 字 段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param in
     * @param pojoClass
     * @param params
     * @param handler
     */
    public static void importExcelBySax(InputStream in, Class<?> pojoClass,
                                        ImportParams params, IReadHandler handler) {
        try {
            new SaxReadExcel().readExcel(in, pojoClass, params, handler);
        } catch (Exception e) {
            log.error("Sax导入Excel数据失败", e);
            throw new BusiException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * Excel 导出
     *
     * @param entity    表格标题属性
     * @param pojoClass Excel对象Class
     * @param dataSet   Excel对象数据List
     */
    public static void exportExcel(ExportParams entity, Class<?> pojoClass,
                                       Collection<?> dataSet,String fileName) {
        Workbook workbook = getWorkbook(entity.getType());
        new ExcelExportService().createSheet(workbook, entity, pojoClass, dataSet);
        downloadExcel(entity,fileName,workbook);
    }

    /**
     * Excel 导出
     *
     * @param entity     表格标题属性
     * @param entityList 构造Map结构中的file对应的表头名称。例：entityList.add(new ExcelExportEntity("性别", "sex"));
     * @param dataSet    Excel对象数据List。例：List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
     */
    public static void exportExcel(ExportParams entity, List<ExcelExportEntity> entityList,
                                       Collection<?> dataSet,String fileName) {
        Workbook workbook = getWorkbook(entity.getType());
        new ExcelExportService().createSheetForMap(workbook, entity, entityList, dataSet);
        downloadExcel(entity,fileName,workbook);
    }

    /**
     * excel导出大批量数据
     * 注：使用完exportBigExcel之后需要调用closeExportBigExcel
     *
     * @param entity 表格标题属性
     * @param pojoClass  Excel对象Class
     * @param dataSet  Excel对象数据List
     */
    public static Workbook exportBigExcel(ExportParams entity, Class<?> pojoClass,
                                          Collection<?> dataSet) {
        ExcelBatchExportService batachServer = ExcelBatchExportService
                .getExcelBatchExportService(entity, pojoClass);
        return batachServer.appendData(dataSet);
    }

    /**
     * 关闭大批量数据导出服务
     *
     * @return
     */
    public static void closeExportBigExcel(ExportParams entity, Class<?> pojoClass) {
        ExcelBatchExportService batachServer = ExcelBatchExportService.getExcelBatchExportService(entity, pojoClass);
        batachServer.closeExportBigExcel();
    }

    /**
     * 根据excel类型返回不同的版本的WorkBook
     *
     * @param excelType
     * @return
     */
    static Workbook getWorkbook(ExcelType excelType){
        return excelType.equals(ExcelType.HSSF) ? new HSSFWorkbook() : new XSSFWorkbook();
    }

    /**
     * 下载Excel文件
     *
     * @param entity
     * @param fileName
     * @param workbook
     * @throws IOException
     */
    static void downloadExcel(ExportParams entity,String fileName, Workbook workbook) {
        try {
            if(entity.getType().equals(ExcelType.HSSF)){
                downloadXls(fileName,workbook);
            } else {
                downloadXlsx(fileName,workbook);
            }
        } catch (IOException e) {
            log.error("下载文件失败",e);
            throw new BusiException(e.getMessage());
        }
    }

    /**
     * 下载Xls文件
     *
     * @param downloadFileName
     * @param workbook
     * @throws IOException
     */
    static void downloadXls(String downloadFileName, Workbook workbook) throws IOException {
        download(downloadFileName, workbook, ".xls");
    }

    /**
     * 下载Xlsx文件
     *
     * @param downloadFileName
     * @param workbook
     * @throws IOException
     */
    static void downloadXlsx(String downloadFileName, Workbook workbook) throws IOException {
        download(downloadFileName, workbook, ".xlsx");
    }

    /**
     * 下载文件
     *
     * @param downloadFileName
     * @param workbook
     * @param fileSuffix
     * @throws IOException
     */
    static void download(String downloadFileName, Workbook workbook, String fileSuffix) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletResponse response = servletRequestAttributes.getResponse();
        assert response != null;
        try (ServletOutputStream out = response.getOutputStream()) {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/msexcel");
            String attachment = "attachment;filename=" + URLEncoder.encode(downloadFileName, "utf-8") + fileSuffix;
            response.setHeader("Content-Disposition", attachment);
            workbook.write(out);
            out.flush();
        }
    }
}
