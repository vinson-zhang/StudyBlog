package com.xyds.studyblog.bean;

import java.util.Date;

import javax.persistence.Entity;

public class User {
	/**
	 * 用户id
	 */
	private int id;
	
	/**
	 * 用户邮箱，也作为用户名
	 */
	private String email;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 *  '用户性别 0:男;1:女',
	 */
	private int gender;
	
	/**
	 * 用户昵称
	 */
	private String nickname;
	
	/**
	 * 用户手机号
	 */
	private String phone;
	
	/**
	 * 用户年龄
	 */
	private int age;
	
	/**
	 * 用户头像地址
	 */
	private String headPortrait;
	
	/**
	 * 用户创建时间
	 */
	private Date createTime;
	
	/**
	 * '0：未激活；1：激活；邮箱是否被激活',
	 */
	private int isActive;
	
	/**
	 * '用户是否可用  0：不可用，1：可用',是否被锁定
	 */
	private int isAvailable;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	
	

}
