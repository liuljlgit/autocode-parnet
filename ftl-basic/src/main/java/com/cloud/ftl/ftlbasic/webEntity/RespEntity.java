package com.cloud.ftl.ftlbasic.webEntity;

public class RespEntity {

	/**
	 * 正确返回
	 * @return
	 */
	public static CommonResp ok(){
		return new CommonResp();
	}

	/**
	 * 返回对象
	 * @param data
	 * @return
	 */
	public static CommonResp ok(Object data){
		return new CommonResp(data);
	}

	/**
	 * 错误返回
	 * @param codeEnum
	 * @return
	 */
	public static CommonResp error(CodeEnum codeEnum){
		return new CommonResp(codeEnum);
	}

	/**
	 * 错误返回
	 * @param code
	 * @param msg
	 * @return
	 */
	public static CommonResp error(Integer code,String msg){
		return new CommonResp(code,msg);
	}

}
