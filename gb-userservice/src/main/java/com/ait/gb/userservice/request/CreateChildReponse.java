package com.ait.gb.userservice.request;

import com.ait.gb.userservice.vo.ChildVO;

public class CreateChildReponse {
  
	private String code;
	private ChildVO vo;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ChildVO getVo() {
		return vo;
	}
	public void setVo(ChildVO vo) {
		this.vo = vo;
	}
	
	
	
}
