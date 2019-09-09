package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@Slf4j
@RestController
@Validated
@RequestMapping("/loadtime")
@Api(tags = "LoadTime")
public class LoadTimeCtrl{

    @Autowired
    private ILoadTimeService loadTimeService;

    @GetMapping(value = "/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="ltId", value="主键",required = true)
    public CommonResp selectById(@RequestParam("ltId") @NotNull Long ltId) {
        return RespEntity.ok(loadTimeService.selectById(ltId,"没有符合条件的记录！"));
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    public CommonResp selectList(@RequestBody LoadTime loadTime){
        return RespEntity.ok(loadTimeService.selectList(loadTime));
    }

    @PostMapping(value = "/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    public CommonResp selectPage(@RequestBody LoadTime loadTime) {
        return RespEntity.ok(loadTimeService.selectPage(loadTime));
    }


    @PostMapping(value = "/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    public CommonResp save(@RequestBody LoadTime loadTime) {
        loadTimeService.save(loadTime);
        return RespEntity.ok();
    }

    @DeleteMapping(value = "/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="ltId", value="主键",required = true)
    public CommonResp deleteById(@RequestParam(value="ltId") @NotNull Long ltId) {
        loadTimeService.deleteById(ltId);
        return RespEntity.ok();
    }

}
