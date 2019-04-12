package com.cloud.ftl.ftltest.test.controller;

import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.utils.BeanUtil;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.query.DailyAmountQuery;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.webentity.req.DailyAmountReq;
import com.cloud.ftl.ftltest.test.webentity.resp.DailyAmountResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * DailyAmountCtrl 控制层方法
 * @author lijun
 */
@RestController
public class DailyAmountCtrl{

  @Autowired
  private IDailyAmountService dailyAmountService;


    /**
    * DailyAmount 根据主键获取单个数据
    * @return
    * @throws Exception
    */
    @GetMapping(value = "/dailyamount/{daId}")
    public String loadDailyAmountByKey(@PathVariable(value="daId") Long daId) throws Exception {
        if(Objects.isNull(daId)){
         throw new BusiException("请输入要获取的数据的ID") ;
        }
        DailyAmount dailyAmount = dailyAmountService.loadDailyAmountByKey(daId);
        return RespEntity.ok(new DailyAmountResp(dailyAmount));
    }

   /**
    * DailyAmount 根据实体对象查询列表
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/dailyamount/list")
    public String getDailyAmountPageList(@RequestBody DailyAmountReq dailyAmountReq) throws Exception {
        DailyAmountQuery query = BeanUtil.createBean(dailyAmountReq, DailyAmountQuery.class);
        query.setEntityId(10000);
        query.setDateTime(Opt.LESS,new Date());
        query.andCriteria().and(DailyAmount.TABLE_DA_ID,Opt.IN,new ArrayList<Long>(){{
            add(1000009L);
            add(1000010L);
            add(1000011L);
            add(1000012L);
        }}).or(DailyAmount.TABLE_PROFIT_HOURS,Opt.BETWEEN,1,100);
        query.setOrderByClause(DailyAmount.TABLE_DA_ID+" "+Opt.DESC);
        return RespEntity.ok(dailyAmountService.findDailyAmountList(query));
    }

    /**
    * DailyAmount 新增或者修改记录，根据主键判断，主键为空则新增，否则修改。
    * @return
    * @throws Exception
    */
    @PostMapping(value = "/dailyamount")
    public String saveDailyAmount(@RequestBody DailyAmountReq dailyAmountReq) throws  Exception{
        DailyAmount dailyAmount = BeanUtil.createBean(dailyAmountReq, DailyAmount.class);
        dailyAmountService.saveDailyAmount(dailyAmount);
        return RespEntity.ok();
    }

    /**
    * DailyAmount 根据主键删除数据
    * @return
    * @throws Exception
    */
    @DeleteMapping(value = "/dailyamount/{daId}")
    public String deleteDailyAmount(@PathVariable(value="daId") Long daId) throws  Exception{
        if(Objects.isNull(daId)){
           throw new BusiException("删除主键不可为空") ;
        }
        dailyAmountService.deleteDailyAmount(daId);
        return RespEntity.ok();
    }

	//------------------------ custom code begin ------------------------//
    
	//======================== custom code end ========================//

}
