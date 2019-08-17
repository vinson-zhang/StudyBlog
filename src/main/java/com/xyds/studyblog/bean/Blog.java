package com.xyds.studyblog.bean;

import java.util.Date;

/**
 * 博客主题
 * @author 张涛
 *
 */
public class Blog {
	
	//博客ID
	private String id;
	
	//博客名称
	private String name;
	
	//博客作者
	private String author;
	
	//博客内容
	private String content;
	
	//博客创建时间
	private Date createTime;
	
	//博客后一次修改的时间
	private Date modifyTime;
	
	//是否外人可见,可见是：1，不可见是：0
	private int isVisible;
	
	//blog阅读次数
	private int readTimes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	
	public int getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(int isVisible) {
		this.isVisible = isVisible;
	}

	public int getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(int readTimes) {
		this.readTimes = readTimes;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", name=" + name + ", author=" + author
				+ ", content=" + content + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", isVisible=" + isVisible
				+ "]";
	}

	
	
}
