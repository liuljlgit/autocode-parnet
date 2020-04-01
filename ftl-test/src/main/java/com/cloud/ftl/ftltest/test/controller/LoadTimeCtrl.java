package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.ftl.ftltest.test.service.ILoadTimeService;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/loadtime")
@Api(tags = "文档信息",hidden = true)
public class LoadTimeCtrl{

    @Autowired
    private ILoadTimeService loadTimeService;

    @GetMapping(value = "/obj")
    @ApiOperation(value = "根据主键查询" , tags = "xxx",hidden = true, notes = "author: llj")
    @ApiImplicitParam(name="ltId", value="主键",required = true)
    public CommonResp<LoadTime> selectById(@RequestParam("ltId") @NotNull Long ltId) {
        return RespEntity.ok(loadTimeService.selectById(ltId,"没有符合条件的记录！"));
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询所有列表" , tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<List<LoadTime>> selectList(@RequestBody LoadTime loadTime){
        return RespEntity.ok(loadTimeService.selectList(loadTime));
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询" , tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<PageBean<LoadTime>> selectPage(@RequestBody LoadTime loadTime) {
        return RespEntity.ok(loadTimeService.selectPage(loadTime));
    }


    @PostMapping(value = "/obj")
    @ApiOperation(value = "更新或者新增", tags = "xxx",hidden = true, notes = "author: llj")
    public CommonResp<Object> save(@RequestBody LoadTime loadTime) {
        loadTimeService.save(loadTime);
        return RespEntity.ok();
    }

    @DeleteMapping(value = "/obj")
    @ApiOperation(value = "根据主键删除",tags = "xxx",hidden = true, notes = "author: llj")
    @ApiImplicitParam(name="ltId", value="主键",required = true)
    public CommonResp<Object> deleteById(@RequestParam(value="ltId") @NotNull Long ltId) {
        loadTimeService.deleteById(ltId);
        return RespEntity.ok();
    }

}
