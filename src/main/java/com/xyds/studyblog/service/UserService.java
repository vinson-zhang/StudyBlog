package com.xyds.studyblog.service;

import java.util.Map;

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
	
	/**
	 * 配合bootstrap表单验证插件，验证用户名是否可用
	 * @return
	 */
	public Map<String,Boolean> usernameIsAvailable(String username);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 * 返回 1 表示成功，返回 0，表示插入失败
	 */
	public int addUser(User user);

}
