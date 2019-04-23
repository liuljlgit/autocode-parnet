package com.cloud.ftl.ftltest.test.webentity.resp;

import com.cloud.ftl.ftlbasic.utils.CommonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;

import java.util.Date;

/**
 * DailyAmountResp 返回实体类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyAmountResp extends DailyAmount{

	public DailyAmountResp(DailyAmount dailyAmount){
		CommonUtil.copyPropertiesIgnoreNull(dailyAmount,this);
	}


	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getDateTime() {
		return super.getDateTime();
	}

	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return super.getCreateTime();
	}

	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getStatusTime() {
		return super.getStatusTime();
	}
}
