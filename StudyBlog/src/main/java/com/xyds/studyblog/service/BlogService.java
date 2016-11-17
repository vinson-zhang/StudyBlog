package com.xyds.studyblog.service;

import java.util.List;

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
	
	/**
	 * 根据博客ID查询博客详细信息
	 * @param blogId
	 * @return
	 */
	public Blog selectBlogById(int blogId);
	
	/**
	 * 根据用户名，即email查询用户所有的博客，分页，（暂时不分页）
	 * @param username
	 * @return
	 */
	public List<Blog> selectMyBlogsByPage(String username);

}
