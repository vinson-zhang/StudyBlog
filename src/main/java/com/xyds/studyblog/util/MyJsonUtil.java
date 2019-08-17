package com.xyds.studyblog.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 我的json工具
 * @author 张涛
 *
 */
public class MyJsonUtil {
	
	/**
	 * 将object变成jsonString，并加上两边的括号，使之成为js所能识别的JSON
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object){
		String result = JSONObject.toJSONString(object);
		result = "("+result+")";
		return result;
	}

}
