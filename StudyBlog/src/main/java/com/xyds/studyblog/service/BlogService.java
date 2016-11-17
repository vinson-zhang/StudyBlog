package com.xyds.studyblog.service;

import org.springframework.stereotype.Service;

import com.xyds.studyblog.bean.Blog;

/**
 * 博客相关业务的服务类
 * @author 张涛
 *
 */
public interface BlogService {
	
	/**
	 * 添加blog
	 * 返回值 1：插入正常
	 *      0：插入出现问题
	 * @param blog
	 * @return
	 */
	public int addBlog(Blog blog);

}
