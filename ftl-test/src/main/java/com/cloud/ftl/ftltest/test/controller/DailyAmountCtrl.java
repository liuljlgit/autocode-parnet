package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/dailyamount")
@Api(tags = "DailyAmount")
public class DailyAmountCtrl{

    @Autowired
    private IDailyAmountService dailyAmountService;

    @GetMapping(value = "/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="daId", value="主键",required = true)
    public CommonResp<DailyAmount> selectById(@RequestParam("daId") Long daId) {
        if(Objects.isNull(daId)){
            throw new BusiException("请输入要获取的数据的ID") ;
        }
        return RespEntity.ok(dailyAmountService.selectById(daId,"没有符合条件的记录！"));
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    public CommonResp<DailyAmount> selectList(@RequestBody DailyAmount dailyAmount){
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
    public CommonResp<Object> deleteById(@RequestParam(value="daId") Long daId) {
        if(Objects.isNull(daId)){
           throw new BusiException("删除主键不可为空") ;
        }
        dailyAmountService.deleteById(daId);
        return RespEntity.ok();
    }

}
