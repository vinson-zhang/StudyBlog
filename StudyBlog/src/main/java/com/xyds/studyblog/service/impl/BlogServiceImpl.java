package com.xyds.studyblog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyds.studyblog.bean.Blog;
import com.xyds.studyblog.bean.Blog2;
import com.xyds.studyblog.dao.blog.BlogDao;
import com.xyds.studyblog.exception.CustomerException;
import com.xyds.studyblog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Override
	public int addBlog(Blog blog) {
		try {
			blogDao.addBlog(blog);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Blog2 selectBlogById(int blogId) {
		Blog2 blog = blogDao.selectBlogById(blogId);
		
		return blog;
	}

	@Override
	public List<Blog> selectMyBlogsByPage(String username) {
		List<Blog> blogs = blogDao.selectMyBlogsByPage(username);
		return blogs;
	}

	@Override
	public Map<String, Object> selectUserInfo(String userId) {
		List<Blog> blogs = blogDao.selectBlogsByUserId(userId);
		Map<String,Object> results = new HashMap<String,Object>();
		results.put("sum", blogs.size()+"");
		return results;
	}
	
	@Override
	public List<Blog2> selectAllBlogsByUserId(String userId) {
		List<Blog2> blogs = blogDao.selectBlog2sByUserId(userId);
		return blogs;
	}
	
	@Override
	public void addReadTimes(int blogId) {
		try {
			blogDao.addReadTimes(blogId);
		} catch (Exception e) {
			System.out.println("增加阅读次数异常");
			e.printStackTrace();
			
		}
	}
	
	

}
