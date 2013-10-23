package com.ait.gb.entity;

import org.springframework.data.annotation.Id;

public class UserEntity {
  
	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private String imageMax;
	private String imageSmall;
	private String createTime;
	private String childNumber;
	private int random;
	
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
	public String getImageMax() {
		return imageMax;
	}
	public void setImageMax(String imageMax) {
		this.imageMax = imageMax;
	}
	public String getImageSmall() {
		return imageSmall;
	}
	public void setImageSmall(String imageSmall) {
		this.imageSmall = imageSmall;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getRandom() {
		return random;
	}
	public void setRandom(int random) {
		this.random = random;
	}
	public String getChildNumber() {
		return childNumber;
	}
	public void setChildNumber(String childNumber) {
		this.childNumber = childNumber;
	}
	
	
	
	
}
