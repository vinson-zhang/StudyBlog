package com.xyds.studyblog.dao.blog;

import java.util.List;

import com.xyds.studyblog.bean.Blog;
import com.xyds.studyblog.bean.Blog2;

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
	public Blog2 selectBlogById(int blogId);
	
	/**
	 * 根据用户名查询，分页查询自己的blog
	 * @param username
	 * @return
	 */
	public List<Blog> selectMyBlogsByPage(String username);
	
	
	/**
	 * 根据用户ID查询用户的所有blog
	 */
	public List<Blog> selectBlogsByUserId(String userId);
	
	/**
	 * 设置关联查询，查询blog的时候并将其用户信息查出来
	 * @param userId
	 * @return
	 */
	public List<Blog2> selectBlog2sByUserId(String userId);
	
	/**
	 * 增加阅读次数
	 * @param blogId
	 */
	public void addReadTimes(int blogId);

}
