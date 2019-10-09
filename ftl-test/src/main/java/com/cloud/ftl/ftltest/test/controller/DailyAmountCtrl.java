package com.cloud.ftl.ftltest.test.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.util.PoiMergeCellUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cloud.ftl.ftlbasic.excel.CustomExcelUtil;
import com.cloud.ftl.ftlbasic.excel.DefaultExportStyleHandler;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.excel.EasyPoiExcelUtil;
import com.cloud.ftl.ftlbasic.excel.EasyPoiErrorResp;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftltest.test.excel.DailyAmountEasyPoi;
import com.cloud.ftl.ftltest.test.excel.TestRead;
import com.cloud.ftl.ftltest.test.excel.TestReadListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/dailyamount")
@Api(tags = "日前报量管理")
public class DailyAmountCtrl{

    @Autowired
    private IDailyAmountService dailyAmountService;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="daId", value="主键",required = true)
    public CommonResp<DailyAmount> selectById(@RequestParam("daId") @NotNull Long daId) {
        return RespEntity.ok(dailyAmountService.selectById(daId,"没有符合条件的记录！"));
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    public CommonResp<List<DailyAmount>> selectList(@RequestBody DailyAmount dailyAmount){
        return RespEntity.ok(dailyAmountService.selectList(dailyAmount));
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    public CommonResp<PageBean<DailyAmount>> selectPage(@RequestBody DailyAmount dailyAmount) {
        return RespEntity.ok(dailyAmountService.selectPage(dailyAmount));
    }


    @PostMapping(value = "/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    public CommonResp<Object> save(@RequestBody DailyAmount dailyAmount) {
        dailyAmountService.save(dailyAmount);
        return RespEntity.ok();
    }

    @DeleteMapping(value = "/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="daId", value="主键",required = true)
    public CommonResp<Object> deleteById(@RequestParam(value="daId") @NotNull Long daId) {
        dailyAmountService.deleteById(daId);
        return RespEntity.ok();
    }

    @GetMapping(value = "/export")
    @ApiOperation(value = "导出数据" , notes = "author: llj")
    public void export() {
        try {
            JSONArray.parseArray(JSON.toJSONString("aa"),Long.class);
            ExportParams exportParams = new ExportParams("大数据测试","大数据测试", ExcelType.XSSF);
            exportParams.setStyle(DefaultExportStyleHandler.class);
            Workbook workbook = null;
            List<DailyAmountEasyPoi> list = Lists.newArrayList();
            for (int i = 0; i < 10000; i++) {
                DailyAmountEasyPoi dailyAmountExcel = new DailyAmountEasyPoi();
                dailyAmountExcel.setDaId((long) i);
                dailyAmountExcel.setDateTime(new Date());
                dailyAmountExcel.setName("lijun" + i);
                list.add(dailyAmountExcel);
                if(list.size() == 1000 || i == 9999){
                    workbook = cn.afterturn.easypoi.excel.ExcelExportUtil.exportBigExcel(exportParams, DailyAmountEasyPoi.class, list);
                    list.clear();
                }
            }
            cn.afterturn.easypoi.excel.ExcelExportUtil.closeExportBigExcel();
            assert workbook != null;
            EasyPoiExcelUtil.downloadExcel(workbook,"大数据测试",ExcelType.XSSF);
        } catch (IOException e) {
            log.error("下载数据失败",e);
            throw new BusiException(e.getMessage());
        }
    }

    @PostMapping(value = "/import")
    @ApiOperation(value = "导入数据" , notes = "author: llj")
    @ApiImplicitParam(name="file", value="导入文件",required = true)
    public List<EasyPoiErrorResp> importData(@RequestParam("file") MultipartFile file) {
        List<EasyPoiErrorResp> errors = Lists.newArrayList();
        ImportParams importParams = new ImportParams();
        importParams.setNeedVerify(true);
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        importParams.setStartRows(0);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheetAt = workbook.getSheetAt(0);
        int physicalNumberOfCells = sheetAt.getRow(0).getPhysicalNumberOfCells();
        try {
            ExcelImportResult<DailyAmountEasyPoi> importExcelMore = ExcelImportUtil.importExcelMore(file.getInputStream(), DailyAmountEasyPoi.class, importParams);
            List<DailyAmountEasyPoi> succList = importExcelMore.getList();
            if(importExcelMore.isVerfiyFail()){
                List<DailyAmountEasyPoi> failList = importExcelMore.getFailList();
                for (DailyAmountEasyPoi fail : failList) {
                    EasyPoiErrorResp error = new EasyPoiErrorResp(fail.getErrorMsg(),fail.getRowNum());
                    errors.add(error);
                }
            }
        } catch (Exception e) {
            log.error("导入数据失败",e);
            throw new BusiException(e.getMessage());
        }
        return errors;
    }

    @PostMapping(value = "/alibaba/import")
    @ApiOperation(value = "导入数据" , notes = "author: llj")
    @ApiImplicitParam(name="file", value="导入文件",required = true)
    public void alibabaImportData(@RequestParam("file") MultipartFile file) {
        try {
            dailyAmountService.saveExcel(file.getInputStream());
        } catch (Exception e) {
            log.error("导入数据失败",e);
            throw new BusiException(e.getMessage());
        }
    }

    @PostMapping(value = "/custom/import")
    @ApiOperation(value = "导入数据" , notes = "author: llj")
    @ApiImplicitParam(name="file", value="导入文件",required = true)
    public void customImportData(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Map<String, List<BigDecimal>> mapDatas = CustomExcelUtil.parseCellsData(workbook, 0, 0,
                    1, 1, 26, false,BigDecimal.class);
            System.out.println(mapDatas);
            log.info("----------------------");
        } catch (Exception e) {
            log.error("导入数据失败",e);
            throw new BusiException(e.getMessage());
        }
    }

    @PostMapping(value = "/custom/import2")
    @ApiOperation(value = "导入数据" , notes = "author: llj")
    @ApiImplicitParam(name="file", value="导入文件",required = true)
    public void customImportData2(@RequestParam("file") MultipartFile file) {
        try {
            ArrayList<Integer[]> listsMap = Lists.newArrayList(new Integer[]{1, 1, 1, 2}, new Integer[]{11, 24, 1, 2});
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            List<BigDecimal> bigDecimals = CustomExcelUtil.parseRowsData(workbook, 0, listsMap, true, BigDecimal.class);
            System.out.println(bigDecimals);
            log.info("----------------------");
        } catch (Exception e) {
            log.error("导入数据失败",e);
            throw new BusiException(e.getMessage());
        }
    }

    @GetMapping(value = "/temp/export")
    @ApiOperation(value = "导出数据" , notes = "author: llj")
    public void fe_map() throws Exception {
        String location = "classpath:" + Paths.get("/temp", "aa.xlsx").toString();
        Resource rs = resourceLoader.getResource(location);
        TemplateExportParams params = new TemplateExportParams(rs.getURL().getPath());
        params.setColForEach(true);
        Map<String, Object> map = Maps.newHashMap();
        map.put("biaoti","这是标题");
        List<Map<String, Object>> listMap = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> lm = Maps.newHashMap();
            lm.put("genName", "机组"+i);
            for(int j = 1;j<=24;j++){
                lm.put("data"+j,"数据"+i+j);
            }
            listMap.add(lm);
        }
        map.put("maplist", listMap);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        PoiMergeCellUtil.addMergedRegion(workbook.getSheetAt(0),0,0,0,4);
        EasyPoiExcelUtil.downloadExcel(workbook,"test",ExcelType.XSSF);
    }

    @GetMapping(value = "/temp/export2")
    @ApiOperation(value = "导出数据2" , notes = "author: llj")
    public void fe_map2() throws Exception {
        String location = "classpath:" + Paths.get("/temp", "cc.xlsx").toString();
        Resource rs = resourceLoader.getResource(location);
        TemplateExportParams params = new TemplateExportParams(rs.getURL().getPath());
        params.setColForEach(true);
        Map<String, Object> excelMap = Maps.newHashMap();
        List<Map<String, Object>> listMap = Lists.newArrayList();
        Date beginDate = new Date();
        String beginDateStrCn = new SimpleDateFormat("yyyy年MM月").format(beginDate);
        Map<String, Object> rowMap = Maps.newHashMap();
        for (int i = 1; i <= 24; i++) {
            int index = i % 6;
            rowMap.put("date" + (index == 0 ? 6 : index),beginDateStrCn + i);
            rowMap.put("data" + (index == 0 ? 6 : index),"数据"+i);
            if(index == 0){
                listMap.add(rowMap);
                rowMap = Maps.newHashMap();
            }
        }
        excelMap.put("maplist", listMap);
        Workbook workbook = ExcelExportUtil.exportExcel(params, excelMap);
        EasyPoiExcelUtil.downloadExcel(workbook,"test",ExcelType.XSSF);
    }

    @GetMapping(value = "/temp/export3")
    @ApiOperation(value = "导出数据3" , notes = "author: llj")
    public void fe_map3() throws Exception {
        String location = "classpath:" + Paths.get("/temp", "gen_out_price_temp.xlsx").toString();
        Resource rs = resourceLoader.getResource(location);
        TemplateExportParams params = new TemplateExportParams(rs.getURL().getPath());
        params.setColForEach(true);
        Map<String, Object> excelMap = Maps.newHashMap();

        //机组数据
        List<Map<String, Object>> maplist = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> lm = Maps.newHashMap();
            lm.put("genName", "机组"+i);
            for(int j = 1;j<=24;j++){
                lm.put("data"+j,"数据"+i+j);
            }
            maplist.add(lm);
        }
        //价格数据
        List<Map<String, Object>> pricelist = Lists.newArrayList();
        for (int i = 0; i < 24; i++) {
            Map<String, Object> lm = Maps.newHashMap();
            lm.put("price","价格" + i);
            pricelist.add(lm);
        }
        //渲染数据
        excelMap.put("maplist", maplist);
        excelMap.put("pricelist", pricelist);
        Workbook workbook = ExcelExportUtil.exportExcel(params, excelMap);
        PoiMergeCellUtil.addMergedRegion(workbook.getSheetAt(0),0,0,1,4);
        EasyPoiExcelUtil.downloadExcel(workbook,"test",ExcelType.XSSF);
    }

}
