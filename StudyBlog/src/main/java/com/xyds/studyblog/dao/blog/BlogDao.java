package com.xyds.studyblog.dao.blog;

import java.util.List;

import com.xyds.studyblog.bean.Blog;

/**
 * 操作类
 * @author 张涛
 *
 */
public interface BlogDao {
	
	/**
	 * 添加blog
	 */
	public void addBlog(Blog blog);
	
	/**
	 * 根据blogID查询其详细信息
	 * @param id
	 * @return
	 */
	public Blog selectBlogById(int blogId);
	
	/**
	 * 根据用户名查询，分页查询自己的blog
	 * @param username
	 * @return
	 */
	public List<Blog> selectMyBlogsByPage(String username);

}
