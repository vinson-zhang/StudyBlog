package com.xyds.studyblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyds.studyblog.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getPassword")
	@ResponseBody
	public  String getPassword(String username){
		String password = userService.getPassword(username);
		System.out.println(password);
		return "main";
	}

}
