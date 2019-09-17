package com.cloud.ftl.ftlbasic.webEntity;

public class RespEntity {

	/**
	 * 正确返回
	 * @return
	 */
	public static <T> CommonResp<T> ok(){
		return new CommonResp<>();
	}

	/**
	 * 返回对象
	 * @param data
	 * @return
	 */
	public static <T> CommonResp<T> ok(T data){
		return new CommonResp<>(data);
	}

	/**
	 * 错误返回
	 * @param codeEnum
	 * @return
	 */
	public static <T> CommonResp<T> error(CodeEnum codeEnum){
		return new CommonResp<>(codeEnum);
	}

	/**
	 * 错误返回
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> CommonResp<T> error(Integer code,String msg){
		return new CommonResp<>(code,msg);
	}

}
