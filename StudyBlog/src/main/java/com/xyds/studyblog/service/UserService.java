package com.xyds.studyblog.service;

import org.springframework.stereotype.Service;

import com.xyds.studyblog.bean.User;

/**
 * 用户相关内容的服务类
 * @author 张涛
 *
 */
public interface UserService {
	
	public String getPassword(String userName);
	
	/**
	 * 根据用户名（邮箱）得到用户相关信息
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);

}
