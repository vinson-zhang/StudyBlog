package com.xyds.studyblog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xyds.studyblog.bean.Blog;
import com.xyds.studyblog.service.BlogService;

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
	public String toMyBlogPage(){
		List<Blog> blogs = blogService.selectMyBlogsByPage("mike");
		
		return "blog/myblog";
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
		Blog blog = blogService.selectBlogById(blogId);
		mav.addObject("blog", blog);
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
		blog.setModifyTime(new Date());
		blog.setAuthor("1");
		System.out.println(blog);
		int result = blogService.addBlog(blog);
		if(result == 1){
			return "success";
		}else{
			return "failed";
		}
	}
	
	
	

}
