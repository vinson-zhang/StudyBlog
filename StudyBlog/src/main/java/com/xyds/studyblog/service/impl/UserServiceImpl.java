package com.xyds.studyblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
