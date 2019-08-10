package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.utils.BeanUtil;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftltest.test.service.inft.ILoadTimeService;
import com.cloud.ftl.ftltest.test.entity.LoadTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

/**
 * LoadTimeCtrl 控制层方法
 * @author lijun
 */
@RestController
public class LoadTimeCtrl{

  @Autowired
  private ILoadTimeService loadTimeService;


    /**
    * LoadTime 根据主键获取单个数据
    * @return
    * @throws Exception
    */
    @GetMapping(value = "/loadtime/{ltId}")
    public CommonResp<LoadTime> loadLoadTimeByKey(@PathVariable(value="ltId") Long ltId) throws Exception {
        if(Objects.isNull(ltId)){
         throw new BusiException("请输入要获取的数据的ID") ;
        }
        LoadTime loadTime = loadTimeService.loadLoadTimeByKey(ltId);
        return RespEntity.ok(loadTime);
    }

   /**
    * LoadTime 根据实体对象查询列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/loadtime/list")
    public CommonResp<PageBean<LoadTime>> getLoadTimePageList(@RequestBody LoadTime query) throws Exception {
        PageBean<LoadTime> pageList = loadTimeService.getLoadTimePageList(query);
        return RespEntity.ok(pageList);
    }

    /**
    * LoadTime 新增或者修改记录，根据主键判断，主键为空则新增，否则修改。
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/loadtime")
    public CommonResp<Object> saveLoadTime(@RequestBody LoadTime loadTime) throws  Exception{
        loadTimeService.saveLoadTime(loadTime);
        return RespEntity.ok();
    }

    /**
    * LoadTime 根据主键删除数据
    * @return
    * @throws Exception
    */
    @DeleteMapping(value = "/loadtime/{ltId}")
    public CommonResp<Object> deleteLoadTime(@PathVariable(value="ltId") Long ltId) throws  Exception{
        if(Objects.isNull(ltId)){
           throw new BusiException("删除主键不可为空") ;
        }
        loadTimeService.deleteLoadTime(ltId);
        return RespEntity.ok();
    }

}
