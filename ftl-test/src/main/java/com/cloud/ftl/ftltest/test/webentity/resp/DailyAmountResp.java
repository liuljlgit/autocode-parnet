package com.cloud.ftl.ftltest.test.webentity.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.cloud.ftl.ftlbasic.utils.CommonUtil;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;

import java.util.Date;

@JSONType(includes = {
	DailyAmount.PROP_DA_ID,
	DailyAmount.PROP_ENTITY_ID,
	DailyAmount.PROP_DATE_TIME,
	DailyAmount.PROP_SETT_PROFIT,
	DailyAmount.PROP_EXPEND_PROFIT,
	DailyAmount.PROP_INCOME_PROFIT,
	DailyAmount.PROP_PROFIT_HOURS,
	DailyAmount.PROP_DEFICIT_HOURS,
	DailyAmount.PROP_MINUS_DEVIATION,
	DailyAmount.PROP_POSITIVE_DEVIATION,
	DailyAmount.PROP_STATUS,
	DailyAmount.PROP_CE,
	DailyAmount.PROP_SA,
	DailyAmount.PROP_CREATE_TIME,
	DailyAmount.PROP_STATUS_TIME
})

/**
 * DailyAmountReq 返回实体类
 */
public class DailyAmountResp extends DailyAmount{

	public DailyAmountResp(DailyAmount dailyAmount){
		CommonUtil.copyPropertiesIgnoreNull(dailyAmount,this);
	}

	
	@Override
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDateTime() {
		return super.getDateTime();
	}
	
	@Override
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return super.getCreateTime();
	}
	
	@Override
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStatusTime() {
		return super.getStatusTime();
	}
}
