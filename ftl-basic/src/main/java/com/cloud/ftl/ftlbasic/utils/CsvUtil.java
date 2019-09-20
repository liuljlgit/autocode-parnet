package com.cloud.ftl.ftlbasic.utils;

import cn.afterturn.easypoi.csv.entity.CsvExportParams;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import cn.afterturn.easypoi.csv.export.CsvExportService;
import cn.afterturn.easypoi.csv.imports.CsvImportService;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.handler.inter.IReadHandler;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

@Slf4j
public class CsvUtil {

    /**
     * Csv 导入流适合大数据导入
     * 导入 数据源IO流,不返回校验结果 导入 字段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param in
     * @param pojoClass
     * @param params
     * @return
     */
    public static <T> List<T> importCsv(InputStream in, Class<?> pojoClass,
                                        CsvImportParams params) {
        try{
            return new CsvImportService().readExcel(in, pojoClass, params, null);
        } catch (Exception e){
            log.error("导入csv数据失败",e);
            throw new BusiException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * Csv 导入流适合大数据导入
     * 导入 数据源IO流,不返回校验结果 导入 字段类型 Integer,Long,Double,Date,String,Boolean
     *
     * @param in
     * @param pojoClass
     * @param params
     * @return
     */
    public static void importCsv(InputStream in, Class<?> pojoClass,
                                 CsvImportParams params, IReadHandler readHandler) {
        try{
            new CsvImportService().readExcel(in, pojoClass, params, readHandler);
        } catch (Exception e){
            log.error("导入csv数据失败",e);
            throw new BusiException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * @param params    表格标题属性
     * @param pojoClass Excel对象Class
     * @param dataSet   Excel对象数据List
     */
    public static void exportCsv(CsvExportParams params, Class<?> pojoClass,
                                 Collection<?> dataSet, String fileName) {
        OutputStream outputStream = null;
        try {
            outputStream = getCsvOutPutStream(fileName);
            new CsvExportService().createCsv(outputStream, params, pojoClass, dataSet);
        } catch (IOException e) {
            log.error("导出csv失败:",e);
            throw new BusiException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 根据Map创建对应的Excel
     *
     * @param params     表格标题属性
     * @param entityList Map对象列表
     * @param dataSet    Excel对象数据List
     */
    public static void exportCsv(CsvExportParams params, List<ExcelExportEntity> entityList,
                                 Collection<?> dataSet,String fileName) {
        OutputStream outputStream = null;
        try {
            outputStream = getCsvOutPutStream(fileName);
            new CsvExportService().createCsvOfList(outputStream, params, entityList, dataSet);
        } catch (IOException e) {
            log.error("导出csv失败:",e);
            throw new BusiException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @throws IOException
     */
    static OutputStream getCsvOutPutStream(String fileName) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletResponse response = servletRequestAttributes.getResponse();
        assert response != null;
        ServletOutputStream out = response.getOutputStream();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/csv");
        String attachment = "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".csv";
        response.setHeader("Content-Disposition", attachment);
        return response.getOutputStream();
    }
}
