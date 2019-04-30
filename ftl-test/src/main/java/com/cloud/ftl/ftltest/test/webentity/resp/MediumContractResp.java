package com.cloud.ftl.ftltest.test.webentity.resp;

import com.cloud.ftl.ftlbasic.utils.CommonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.cloud.ftl.ftltest.test.entity.MediumContract;

import java.util.Date;

/**
 * MediumContractResp 返回实体类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediumContractResp extends MediumContract{

	public MediumContractResp(MediumContract mediumContract){
		CommonUtil.copyPropertiesIgnoreNull(mediumContract,this);
	}


	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getContractStartTime() {
		return super.getContractStartTime();
	}

	@Override
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getContractEndTime() {
		return super.getContractEndTime();
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
