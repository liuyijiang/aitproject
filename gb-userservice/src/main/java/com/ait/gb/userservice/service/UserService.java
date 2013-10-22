package com.ait.gb.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.gb.entity.UserEntity;
import com.ait.mongodb.database.query.QueryBean;
import com.ait.mongodb.database.service.AitMongoDBTemplateService;

@Service
public class UserService {
  
	@Autowired
	private AitMongoDBTemplateService service; 
	
	public void test(){
		UserEntity u = new UserEntity();
		u.setName("liuyijiang");
		System.out.println(service);
		service.persist(u);
	}
	
	public void testquery(){
		QueryBean qy = new QueryBean(null);
		qy.getQuery().put("name", "liuyijiang");
		List<UserEntity> list = service.find(qy, null, UserEntity.class);
		for(UserEntity u : list){
			System.out.println(u.getId() + " _ " + u.getName());
		}
	}
	
	
}
