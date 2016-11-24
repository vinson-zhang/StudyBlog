package com.xyds.studyblog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	//进入登录界面
	@RequestMapping(value="/loginPage")
	public String toLoginPage(){
		return "user/loginPage";
	}
	
	//进入注册界面
	@RequestMapping(value="/signinPage")
	public String toSigninPage(){
		return "user/signinPage";
	}
	
	//进入登录界面
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(){
		return "user/loginPage";
	}
	
	/**
	 * 处理用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public  void login(String username,String password,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	//public ModelAndView login(String username,String password){
		String messageLogin = "";
		String resultPageURL = "";
		//创建用户凭证
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		 System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
	        //获取当前的Subject  
	        Subject currentUser = SecurityUtils.getSubject();  
	        try {  
	            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
	            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
	            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
	            System.out.println("对用户[" + username + "]进行登录验证..验证开始");  
	            currentUser.login(token);  
	            System.out.println("对用户[" + username + "]进行登录验证..验证通过");  
	            messageLogin = "验证通过";
	            resultPageURL = "main";  
	        }catch(UnknownAccountException uae){  
	            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
	            messageLogin = "未知账户";
	        }catch(IncorrectCredentialsException ice){  
	            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
	            messageLogin = "密码不正确";
	        }catch(LockedAccountException lae){  
	            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
	            messageLogin = "账户已锁定";
	        }catch(ExcessiveAttemptsException eae){  
	            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");  
	            messageLogin = "用户名或密码错误次数过多";
	        }catch(AuthenticationException ae){  
	            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
	            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
	            ae.printStackTrace();  
	            messageLogin = "用户名或密码不正确";
	        }  
	        //验证是否登录成功  
	        if(currentUser.isAuthenticated()){  
	            System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
	        }else{  
	            token.clear();  
	            resultPageURL = "user/login";
	        }  
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName(resultPageURL);
	        mav.addObject("messageLogin", messageLogin);
	        //return mav;  
	        if(messageLogin.equals("验证通过")){
	        	request.getRequestDispatcher("/index.jsp").forward(request, response);
	        	
	        }else{
	        	request.setAttribute("messageLogin", messageLogin);
	        	request.getRequestDispatcher("/WEB-INF/views/user/loginPage.jsp").forward(request, response);
	        }
	}
	
	/**
	 * 退出登录
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value="/logout")
	public @ResponseBody void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			subject.logout();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}
