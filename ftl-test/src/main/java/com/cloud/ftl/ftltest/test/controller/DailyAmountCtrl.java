package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.utils.BeanUtil;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * DailyAmountCtrl 控制层方法
 * @author lijun
 */
@Slf4j
@RestController
@RequestMapping("/dailyamount")
public class DailyAmountCtrl{

    @Autowired
    private IDailyAmountService dailyAmountService;

    /**
    * DailyAmount 根据主键获取单个数据
    * @return
    * @throws Exception
    */
    @GetMapping(value = "/obj")
    public CommonResp<DailyAmount> selectById(Long daId) throws BusiException {
        if(Objects.isNull(daId)){
            throw new BusiException("请输入要获取的数据的ID") ;
        }
        return RespEntity.ok(dailyAmountService.selectById(daId,"没有符合条件的记录！"));
    }

   /**
    * DailyAmount 根据实体对象查询所有列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/list")
    public CommonResp<DailyAmount> selectList(@RequestBody DailyAmount entity) throws BusiException {
        return RespEntity.ok(dailyAmountService.selectList(entity));
    }

   /**
    * DailyAmount 根据实体对象查询分页列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/page")
    public CommonResp<PageBean<DailyAmount>> selectPage(@RequestBody DailyAmount entity) throws BusiException {
        return RespEntity.ok(dailyAmountService.selectPage(entity));
    }

    /**
    * DailyAmount 新增或者修改记录，根据主键判断，主键为空则新增，否则修改。
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/obj")
    public CommonResp<Object> save(@RequestBody DailyAmount dailyAmount) throws BusiException {
        DailyAmount da1 = new DailyAmount();
        da1.setDateTime(new Date());
        da1.setStatus((byte)1);
        da1.setEntityId(1000);
        DailyAmount da2 = new DailyAmount();
        da2.setDateTime(new Date());
        da2.setStatus((byte)1);
        da2.setEntityId(1000);
        ArrayList<DailyAmount> list = Lists.newArrayList();
        list.add(da1);
        list.add(da2);
        dailyAmountService.addBatch(list);
        return RespEntity.ok();
    }

    /**
    * DailyAmount 根据主键删除数据
    * @return
    * @throws Exception
    */
    @DeleteMapping(value = "/obj")
    public CommonResp<Object> deleteById(@PathVariable(value="daId") Long daId) throws BusiException {
        if(Objects.isNull(daId)){
           throw new BusiException("删除主键不可为空") ;
        }
        dailyAmountService.deleteById(daId);
        return RespEntity.ok();
    }

}
