package com.ait.gb.userservice.request;

import com.ait.gb.userservice.vo.UserVO;

public class LoginResponse {
    
	private String code;
	private UserVO user;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	
	
}
