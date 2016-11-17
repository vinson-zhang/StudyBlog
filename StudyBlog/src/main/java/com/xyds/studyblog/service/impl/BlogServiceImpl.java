package com.xyds.studyblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyds.studyblog.bean.Blog;
import com.xyds.studyblog.dao.blog.BlogDao;
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

}
