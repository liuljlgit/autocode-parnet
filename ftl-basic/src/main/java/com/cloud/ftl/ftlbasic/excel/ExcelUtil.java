package com.cloud.ftl.ftlbasic.excel;

import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Slf4j
public class ExcelUtil {

    /**
     * 获取下载Csv文件流
     *
     * @param fileName
     * @throws IOException
     */
    public static OutputStream downloadCsvStream(String fileName) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletResponse response = servletRequestAttributes.getResponse();
        assert response != null;
        ServletOutputStream out = response.getOutputStream();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        String attachment = "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".csv";
        response.setHeader("Content-Disposition", attachment);
        return response.getOutputStream();
    }

    /**
     * 下载Excel文件
     *
     * @param fileName
     * @param workbook
     * @param excelType
     * @throws IOException
     */
    public static void downloadExcel(Workbook workbook, String fileName, ExcelType excelType) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletResponse response = servletRequestAttributes.getResponse();
        assert response != null;
        try (ServletOutputStream out = response.getOutputStream()) {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            String attachment = "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + (excelType.equals(ExcelType.HSSF) ? ".xls" : ".xlsx");
            response.setHeader("Content-Disposition", attachment);
            workbook.write(out);
            out.flush();
        }
    }
}
