package com.xyds.studyblog.dao.user;

import org.apache.ibatis.type.Alias;

import com.xyds.studyblog.bean.User;

public interface UserDao {
	
	public String getPassword(String userName);
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
	
	/**
	 * 添加新的用户
	 * @param user
	 * @return
	 */
	public void addUser(User user);

}
