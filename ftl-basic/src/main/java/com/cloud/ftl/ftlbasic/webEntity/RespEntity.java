package com.cloud.ftl.ftlbasic.webEntity;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class RespEntity {

	/**
	 * 无返回体的返回
	 * @return
	 */
	public static String ok(){
		JSONObject respObject = new JSONObject();
		respObject.put("code",CodeEnum.EXEC_OK.getCode());
		respObject.put("msg",CodeEnum.EXEC_OK.getMsg());
		return respObject.toJSONString();
	}

	/**
	 * 有返回体的返回
	 * @param result
	 * @param <T>
	 * @return
	 */
	public static <T> String ok(T result){
		JSONObject respObject = new JSONObject();
		respObject.put("code",CodeEnum.EXEC_OK.getCode());
		respObject.put("msg",CodeEnum.EXEC_OK.getMsg());
		respObject.put("result",result);
		return respObject.toJSONString();
	}

	/**
	 * 有错误返回体的返回
	 * @param codeEnum
	 * @param result
	 * @param <T>
	 * @return
	 */
	public static <T> String error(CodeEnum codeEnum,T result){
		if(Objects.isNull(codeEnum)){
			codeEnum = CodeEnum.EXEC_ERROR;
		}
		JSONObject respObject = new JSONObject();
		respObject.put("code",codeEnum.getCode());
		respObject.put("msg",codeEnum.getMsg());
		respObject.put("result",result);
		return respObject.toJSONString();
	}

	/**
	 * 有错误返回体的自定义返回
	 * @param code
	 * @param msg
	 * @param result
	 * @param <T>
	 * @return
	 */
	public static <T> String error(Integer code,String msg,T result){
		JSONObject respObject = new JSONObject();
		respObject.put("code",code);
		respObject.put("msg",msg);
		respObject.put("result",result);
		return respObject.toJSONString();
	}


}
