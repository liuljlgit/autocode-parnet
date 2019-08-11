package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
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
    public CommonResp<DailyAmount> selectById(Long daId) throws Exception {
        if(Objects.isNull(daId)){
            throw new BusiException("请输入要获取的数据的ID") ;
        }
        DailyAmount check = new DailyAmount();
        check.andDateTime(Opt.EQUAL,new Date());
        check.andCriteria().and(DailyAmount.DA_ID,Opt.IN,new ArrayList<Long>(){{
            add(1000L);
            add(100052L);
        }}).or(DailyAmount.CE,Opt.IS_NOT_NULL);
        DailyAmount dailyAmount = dailyAmountService.selectById(daId);
        if(Objects.isNull(dailyAmount)){
            throw new BusiException("没有符合条件的记录！") ;
        }
        return RespEntity.ok(dailyAmount);
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
        dailyAmountService.save(dailyAmount);
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
