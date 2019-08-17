package com.xyds.studyblog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyds.studyblog.bean.User;
import com.xyds.studyblog.dao.user.UserDao;
import com.xyds.studyblog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public String getPassword(String userName) {
		String password = userDao.getPassword(userName);
		return password;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		user = userDao.getUserByUsername(username);
		return user;
	}
	
	@Override
	public Map<String, Boolean> usernameIsAvailable(String username) {
		Map<String,Boolean> result = new HashMap<String, Boolean>();
		User user = userDao.getUserByUsername(username);
		boolean valid = true;
		if(user != null){
			valid = false;
		}
		result.put("valid", valid);
		return result;
	}
	
	@Override
	public int addUser(User user) {
		try {
			userDao.addUser(user);
		} catch (Exception e) {
			System.out.println("新建用户失败");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

}
