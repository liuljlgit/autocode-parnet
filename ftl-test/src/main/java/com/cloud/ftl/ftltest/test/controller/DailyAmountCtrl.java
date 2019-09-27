package com.cloud.ftl.ftltest.test.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/dailyamount")
@Api(tags = "日前报量管理")
public class DailyAmountCtrl{

    @Autowired
    private IDailyAmountService dailyAmountService;

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

}
