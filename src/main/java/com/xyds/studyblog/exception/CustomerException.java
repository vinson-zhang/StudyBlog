package com.xyds.studyblog.exception;

/**
 * 自定义异常，（第一次使用自定义异常，待完善）
 * @author 张涛
 *
 */
public class CustomerException extends Exception {
	public CustomerException(String exceptionName) {
		super(exceptionName);
	}
}
