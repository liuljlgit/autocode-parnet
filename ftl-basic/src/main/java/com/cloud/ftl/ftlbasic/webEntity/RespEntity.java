package com.cloud.ftl.ftlbasic.webEntity;

import java.util.List;

public class RespEntity {

	/**
	 * 正确返回
	 * @return
	 */
	public static CommonResp<Object> ok(){
		return new CommonResp<>();
	}

	/**
	 * 返回对象
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> CommonResp<T> ok(T data){
		return new CommonResp<>(data);
	}

	/**
	 * 返回列表
	 * @param list
	 * @param <T>
	 * @return
	 */
	public static <T> CommonResp<T> ok(List<T> list){
		return new CommonResp<>(list);
	}

	/**
	 * 错误返回
	 * @param codeEnum
	 * @return
	 */
	public static CommonResp<Object> error(CodeEnum codeEnum){
		return new CommonResp<>(codeEnum);
	}

	/**
	 * 错误返回
	 * @param code
	 * @param msg
	 * @return
	 */
	public static CommonResp<Object> error(Integer code,String msg){
		return new CommonResp<>(code,msg);
	}

}
