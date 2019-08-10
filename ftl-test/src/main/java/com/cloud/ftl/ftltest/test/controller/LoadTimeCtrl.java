package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

/**
 * LoadTimeCtrl 控制层方法
 * @author lijun
 */
@Slf4j
@RestController
@RequestMapping("/loadtime")
public class LoadTimeCtrl{

    @Autowired
    private ILoadTimeService loadTimeService;

    /**
    * LoadTime 根据主键获取单个数据
    * @return
    * @throws Exception
    */
    @GetMapping(value = "/obj")
    public CommonResp<LoadTime> selectById(@PathVariable(value="ltId") Long ltId) throws Exception {
        if(Objects.isNull(ltId)){
            throw new BusiException("请输入要获取的数据的ID") ;
        }
        LoadTime loadTime = loadTimeService.selectById(ltId);
        if(Objects.isNull(loadTime)){
            throw new BusiException("没有符合条件的记录！") ;
        }
        return RespEntity.ok(loadTime);
    }

   /**
    * LoadTime 根据实体对象查询列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/page")
    public CommonResp<PageBean<LoadTime>> selectPage(@RequestBody LoadTime query) throws Exception {
        return RespEntity.ok(loadTimeService.selectPage(query));
    }

    /**
    * LoadTime 新增或者修改记录，根据主键判断，主键为空则新增，否则修改。
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/obj")
    public CommonResp<Object> save(@RequestBody LoadTime loadTime) throws  Exception{
        loadTimeService.saveLoadTime(loadTime);
        return RespEntity.ok();
    }

    /**
    * LoadTime 根据主键删除数据
    * @return
    * @throws Exception
    */
    @DeleteMapping(value = "/obj")
    public CommonResp<Object> deleteById(@PathVariable(value="ltId") Long ltId) throws  Exception{
        if(Objects.isNull(ltId)){
           throw new BusiException("删除主键不可为空") ;
        }
        loadTimeService.deleteLoadTime(ltId);
        return RespEntity.ok();
    }

}
