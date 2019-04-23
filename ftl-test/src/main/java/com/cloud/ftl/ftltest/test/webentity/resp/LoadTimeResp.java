package com.cloud.ftl.ftltest.test.webentity.resp;

import com.cloud.ftl.ftlbasic.utils.CommonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.cloud.ftl.ftltest.test.entity.LoadTime;

import java.util.Date;

/**
 * LoadTimeResp 返回实体类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoadTimeResp extends LoadTime{

	public LoadTimeResp(LoadTime loadTime){
		CommonUtil.copyPropertiesIgnoreNull(loadTime,this);
	}


	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getStartTime() {
		return super.getStartTime();
	}

	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getEndTime() {
		return super.getEndTime();
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
