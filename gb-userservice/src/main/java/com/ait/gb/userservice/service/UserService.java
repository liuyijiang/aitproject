package com.ait.gb.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.gb.entity.UserEntity;
import com.ait.mongodb.database.service.AitMongoDBTemplateService;

@Service
public class UserService {
  
	@Autowired
	private AitMongoDBTemplateService service; 
	
	public void test(){
		UserEntity u = new UserEntity();
		u.setId("dsa");
		u.setName("dsad");
		System.out.println(service);
		service.persist(u);
	}
	
}
