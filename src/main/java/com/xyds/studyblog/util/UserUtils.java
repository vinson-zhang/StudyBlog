package com.xyds.studyblog.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;

import com.xyds.studyblog.bean.User;

/**
 * 工具类：用户信息工具类
 * @author 张涛
 *
 */
public class UserUtils {
	
	/**
	 * 获取当前session的用户信息
	 * @return
	 */
	public static User gerCurrentUser(){
		User user = null;
		Subject subject = SecurityUtils.getSubject();
		//如果当前subject为空或用户为空
//		if(subject == null || subject.getSession().getAttribute("currentUser")==null){
//			throw new UnknownAccountException();
//		}
		user = (User) subject.getSession().getAttribute("currentUser");
		return user;
	}

}
