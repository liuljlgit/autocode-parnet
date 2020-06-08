package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.ftl.ftltest.test.service.IBaseHolidayService;
import com.cloud.ftl.ftltest.test.entity.BaseHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/baseholiday")
@Api(tags = "全局接口信息")
public class BaseHolidayCtrl{

    @Autowired
    private IBaseHolidayService baseHolidayService;

    @GetMapping(value = "/obj")
    @ApiOperation(value = "根据主键查询" , tags = "xxx",hidden = true, notes = "author: llj")
    @ApiImplicitParam(name="hId", value="主键",required = true)
    public CommonResp<BaseHoliday> selectById(@RequestParam("hId") @NotNull Long hId) {
        return RespEntity.ok(baseHolidayService.selectById(hId,"没有符合条件的记录！"));
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询所有列表" , tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<List<BaseHoliday>> selectList(@RequestBody BaseHoliday baseHoliday){
        return RespEntity.ok(baseHolidayService.cacheSelectList(baseHoliday));
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询" , tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<PageBean<BaseHoliday>> selectPage(@RequestBody BaseHoliday baseHoliday) {
        return RespEntity.ok(baseHolidayService.selectPage(baseHoliday));
    }


    @PostMapping(value = "/obj")
    @ApiOperation(value = "更新或者新增", tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<Object> save(@RequestBody BaseHoliday baseHoliday) {
        baseHolidayService.save(baseHoliday);
        return RespEntity.ok();
    }

    @DeleteMapping(value = "/obj")
    @ApiOperation(value = "根据主键删除",tags = "xxx",hidden = true, notes = "author: llj")
    @ApiImplicitParam(name="hId", value="主键",required = true)
    public CommonResp<Object> deleteById(@RequestParam(value="hId") @NotNull Long hId) {
        baseHolidayService.deleteById(hId);
        return RespEntity.ok();
    }

}
