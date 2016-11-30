package com.xyds.studyblog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyds.studyblog.bean.User;
import com.xyds.studyblog.service.UserService;
import com.xyds.studyblog.util.MyJsonUtil;
import com.xyds.studyblog.util.UserUtils;
import com.xyds.studyblog.util.VerifyCodeUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getPassword")
	@ResponseBody
	public String getPassword(String username) {
		String password = userService.getPassword(username);
		System.out.println(password);
		return "main";
	}

	// 进入登录界面
	@RequestMapping(value = "/loginPage")
	public String toLoginPage() {
		return "user/loginPage";
	}

	// 进入注册界面
	@RequestMapping(value = "/signinPage")
	public String toSigninPage() {
		return "user/signinPage";
	}

	// 进入注册界面
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signinPage() {
		return "user/signinPage";
	}

	/**
	 * 用户注册,并上传文件
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(User user, String path,String verifyCode,HttpServletRequest request)
			throws IllegalStateException, IOException {
		String userId = user.getEmail();
		System.out.println(path);
		if (!"".equals(path) && path != null) {
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// 获取文件后缀名
						String suffix = file.getOriginalFilename().substring(
								file.getOriginalFilename().lastIndexOf("."));
						path = "F:/temp/" + userId + suffix;
						// 上传
						file.transferTo(new File(path));
					}
				}
			} 
		}else {
			path = "F:/temp/head.jpeg";
		}
		user.setCreateTime(new Date());
		user.setHeadPortrait(path);
		String verifyCodeTemp = (String) request.getSession().getAttribute("rand");
		userService.addUser(user);
		return "user/loginPage";
	}

	// 进入登录界面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "user/loginPage";
	}

	/**
	 * 获取用户自己的头像
	 * 
	 * @param request
	 * @param response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/headPhoto")
	public void uploadHeadPhoto(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		User user = UserUtils.gerCurrentUser();
		String imgFile = "F:/temp/head.jpeg";
		if (user != null) {
			imgFile = user.getHeadPortrait(); // 文件名+路径
		}
		FileInputStream fileIs = null;
		try {
			fileIs = new FileInputStream(imgFile);
		} catch (Exception e) {
			// log.error("系统找不到图像文件："+path+"/"+imgFile);
			System.out.println("找不到指定文件");
			return;
		}
		int i = fileIs.available(); // 得到文件大小
		byte data[] = new byte[i];
		fileIs.read(data); // 读数据
		response.setContentType("image/*"); // 设置返回的文件类型
		OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		outStream.write(data); // 输出数据
		outStream.flush();
		outStream.close();
		fileIs.close();
	}

	/**
	 * 验证用户名（邮箱）是否可用
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/usernameIsAvailable", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	Boolean usernameIsAvailable(String email) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result = userService.usernameIsAvailable(email);
		return result.get("valid");
	}

	/**
	 * 处理用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(String username, String password,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// public ModelAndView login(String username,String password){
		String messageLogin = "";
		String resultPageURL = "";
		// 创建用户凭证
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		System.out.println("为了验证登录用户而封装的token为"
				+ ReflectionToStringBuilder.toString(token,
						ToStringStyle.MULTI_LINE_STYLE));
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			System.out.println("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			System.out.println("对用户[" + username + "]进行登录验证..验证通过");
			messageLogin = "验证通过";
			resultPageURL = "main";
		} catch (UnknownAccountException uae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			messageLogin = "未知账户";
		} catch (IncorrectCredentialsException ice) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			messageLogin = "密码不正确";
		} catch (LockedAccountException lae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			messageLogin = "账户已锁定";
		} catch (ExcessiveAttemptsException eae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			messageLogin = "用户名或密码错误次数过多";
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			messageLogin = "用户名或密码不正确";
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			System.out.println("用户[" + username
					+ "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
		} else {
			token.clear();
			resultPageURL = "user/login";
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName(resultPageURL);
		mav.addObject("messageLogin", messageLogin);
		// return mav;
		if (messageLogin.equals("验证通过")) {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);

		} else {
			request.setAttribute("messageLogin", messageLogin);
			request.getRequestDispatcher("/WEB-INF/views/user/loginPage.jsp")
					.forward(request, response);
		}
	}
	
	
	/**
	 * 
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping(value="/verifyCodeIsCorrect")
	public @ResponseBody boolean verifyCodeIsCorrect(String verifyCode,HttpServletRequest request){
		String tempVerifyCode = (String) request.getSession().getAttribute("rand");
		if(tempVerifyCode.equalsIgnoreCase(verifyCode)){
			request.getSession().removeAttribute("rand");
			return true;
		}
		return false;
	}

	/**
	 * 退出登录
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/logout")
	public @ResponseBody
	void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.getSession().removeAttribute("currentUser");
			subject.logout();
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
	}
	
	@RequestMapping(value="/getVerifyCodeImage")
	public @ResponseBody void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		   response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/jpeg");  
	          
	        //生成随机字串  
	        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
	        //存入会话session  
	        HttpSession session = request.getSession(true);  
	        session.setAttribute("rand", verifyCode.toLowerCase());  
	        //生成图片  
	        int w = 200, h = 80;  
	        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);  
	}

}
