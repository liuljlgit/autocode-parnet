package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.annotation.PatternDate;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@Validated
@RequestMapping("/dailyamount")
@Api(tags = "【日前】1、日前报量管理")
public class DailyAmountCtrl{

    @Autowired
    private IDailyAmountService dailyAmountService;

    @PatternDate
    @GetMapping(value = "/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    public CommonResp selectById(@RequestParam(value="daId") @NotNull Long daId) {
        return RespEntity.ok(dailyAmountService.selectById(daId,"没有符合条件的记录！"));
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    public CommonResp selectList(@RequestBody DailyAmount dailyAmount){
        return RespEntity.ok(dailyAmountService.selectList(dailyAmount));
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    public CommonResp selectPage(@RequestBody DailyAmount dailyAmount) {
        return RespEntity.ok(dailyAmountService.selectPage(dailyAmount));
    }


    @PostMapping(value = "/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    public CommonResp save(@RequestBody DailyAmount dailyAmount) {
        dailyAmountService.save(dailyAmount);
        return RespEntity.ok();
    }

    @DeleteMapping(value = "/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="daId", value="主键",required = true)
    public CommonResp deleteById(@RequestParam(value="daId") @NotNull Long daId) {
        dailyAmountService.deleteById(daId);
        return RespEntity.ok();
    }

}
