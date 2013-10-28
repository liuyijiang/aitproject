package com.ait.gb.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ait.code.util.SecurityUtil;
import com.ait.gb.entity.ChildEntity;
import com.ait.gb.entity.UserEntity;
import com.ait.gb.userservice.request.LoginRequest;
import com.ait.gb.userservice.vo.ChildVO;
import com.ait.gb.userservice.vo.UserVO;
import com.ait.mongodb.database.query.QueryBean;
import com.ait.mongodb.database.query.UpdateBean;
import com.ait.mongodb.database.service.AitMongoDBTemplateService;

@Service
public class UserService {
  
	@Autowired
	private AitMongoDBTemplateService service; 
	
    public UserVO saveUserEntity(UserEntity entity){
    	UserVO vo = null;
    	service.persist(entity);
    	if(entity.getId() != null){
    		vo = new UserVO();
    		vo.setCreateTime(entity.getCreateTime());
    		vo.setEmail(entity.getEmail());
    		vo.setId(entity.getId());
    		vo.setName(entity.getName());
    	}
    	return vo;
    }
	
    public ChildVO saveUserChildEntity(ChildEntity entity){
    	ChildVO vo = null;
    	service.persist(entity);
    	if(entity.getId() != null){
    		vo = new ChildVO();
    		vo.setAddress(entity.getAddress());
    		vo.setCreateTime(entity.getCreateTime());
    		vo.setId(entity.getId());
    		vo.setInfo(entity.getInfo());
    		vo.setName(entity.getName());
    		vo.setParentId(entity.getParentId());
    	}
    	return vo;
    }
    
    public boolean updateUserImageOfChildEntity(ChildVO vo){
    	UpdateBean bean = new UpdateBean(vo.getId());
    	bean.getData().put("imageMax", vo.getImageMax());
    	bean.getData().put("imageSmall", vo.getImageSmall());
    	return service.update(bean, ChildEntity.class);
    }
    
    public UserVO findUserFromLogin(LoginRequest loginRequest){
    	UserVO vo = null;
    	QueryBean bean = new QueryBean(null);
    	bean.getQuery().put("email", loginRequest.getEmail());
    	bean.getQuery().put("password", SecurityUtil.digestByMd5(loginRequest.getPassword()));
    	UserEntity entity = service.findOne(bean, UserEntity.class);
    	if(entity != null){
    		vo = new UserVO();
    		vo.setCreateTime(entity.getCreateTime());
    		vo.setEmail(entity.getEmail());
    		vo.setId(entity.getId());
    		vo.setName(entity.getName());
    		vo.setImageMax(entity.getImageMax());
    		vo.setImageSmall(entity.getImageSmall());
    	}
    	return vo;
    }
	
}
