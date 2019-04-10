package com.cloud.ftl.ftltest.test.webentity.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.cloud.ftl.ftlbasic.utils.CommonUtil;
import com.cloud.ftl.ftltest.test.entity.LoadTime;

import java.util.Date;

@JSONType(includes = {
	LoadTime.PROP_LT_ID,
	LoadTime.PROP_ENTITY_ID,
	LoadTime.PROP_START_TIME,
	LoadTime.PROP_END_TIME,
	LoadTime.PROP_STATUS,
	LoadTime.PROP_CREATE_TIME,
	LoadTime.PROP_STATUS_TIME
})

/**
 * LoadTimeReq 返回实体类
 */
public class LoadTimeResp extends LoadTime{

	public LoadTimeResp(LoadTime loadTime){
		CommonUtil.copyPropertiesIgnoreNull(loadTime,this);
	}


	@Override
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return super.getStartTime();
	}

	@Override
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return super.getEndTime();
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
