package com.xyds.studyblog.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.alibaba.fastjson.JSONObject;
import com.xyds.studyblog.bean.Blog;
import com.xyds.studyblog.bean.Blog2;
import com.xyds.studyblog.bean.User;
import com.xyds.studyblog.service.BlogService;
import com.xyds.studyblog.util.UserUtils;

/**
 * 用于博客相关内容的控制器
 * @author 张涛
 *
 */

@Controller
@RequestMapping(value="/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	/**
	 * 进入我的博客界面
	 * @return
	 */
	@RequestMapping(value="/myblog")
	public ModelAndView toMyBlogPage(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("currentUser");
		Map<String,Object> results = blogService.selectUserInfo(user.getId()+"");
		results.put("nickname", user.getNickname());
		results.put("blogAge", daysBetween(user.getCreateTime(), new Date())/365);
		results.put("headPhoto", user.getHeadPortrait());
//		List<Blog> blogs = blogService.selectMyBlogsByPage("mike");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("blog/myblog");
		mav.addObject(results);
		request.setAttribute("sum", results.get("sum"));
		request.setAttribute("blogAge", results.get("blogAge"));
		return mav;
	}
	
	/**
	 * 进入写博客界面
	 * @return
	 */
	@RequestMapping(value="/writeBlog")
	public String toWriteBlogPage(){
		return "blog/writeBlog";
	}
	
	/**
	 * 根据blogID 查询blog的详细信息，并跳转到详细信息页面
	 * @param blogId
	 * @return
	 */
	@RequestMapping(value="/blogDetail/{blogId}")
	public ModelAndView toBlogDetail(@PathVariable int blogId){
		ModelAndView mav = new ModelAndView();
		//查询blog的详细信息
		Blog2 blog = blogService.selectBlogById(blogId);
		//增加阅读次数
		blogService.addReadTimes(blogId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String createTime = sdf.format(blog.getCreateTime());
		mav.addObject("blog", blog);
		mav.addObject("createTime", createTime);
		mav.setViewName("blog/blogDetail");
		return mav;
	}
	
	/**
	 * 添加blog
	 * @param blog
	 * @return
	 */
	@RequestMapping(value="/addBlog",method=RequestMethod.POST)
	public @ResponseBody String addBlog(@RequestBody Blog blog){
		Subject subject = SecurityUtils.getSubject();
//		int userId = 0 ;
//		if(subject!=null){
//			if(subject.getSession()!=null){
//				User user = (User) subject.getSession().getAttribute("currentUser");
//				userId = user.getId();
//			}else{
//				
//			}
//		}
		User user = (User) subject.getSession().getAttribute("currentUser");
		blog.setModifyTime(new Date());
		blog.setAuthor(user.getId()+"");
		System.out.println(blog);
		int result = blogService.addBlog(blog);
		if(result == 1){
			return "success";
		}else{
			return "failed";
		}
	}
	
	/**
	 * 获取用户的所有BLog，并返回json数据
	 * @return
	 */
	@RequestMapping(value="/getAllBlogs")
	public @ResponseBody String getAllBlogsOfUser(){
		User user = UserUtils.gerCurrentUser();
		List<Blog2> blogs = blogService.selectAllBlogsByUserId(user.getId()+"");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("blogs", blogs);
		return jsonObject.toJSONString();
	}
	
	
	/**
	 * 获取两个日期之间相隔的天数
	 * @param early
	 * @param late
	 * @return
	 */
	public static final int daysBetween(Date early, Date late) { 
	     
        java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(early);   
         caled.setTime(late);   
         //设置时间为0时   
         calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         calst.set(java.util.Calendar.MINUTE, 0);   
         calst.set(java.util.Calendar.SECOND, 0);   
         caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         caled.set(java.util.Calendar.MINUTE, 0);   
         caled.set(java.util.Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }   

}
