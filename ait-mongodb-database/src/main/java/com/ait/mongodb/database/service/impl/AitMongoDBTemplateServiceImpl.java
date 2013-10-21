package com.ait.mongodb.database.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.ait.mongodb.database.service.AitMongoDBTemplateService;

@Service
public class AitMongoDBTemplateServiceImpl implements AitMongoDBTemplateService {
  
	public static final Logger logger = LoggerFactory.getLogger(AitMongoDBTemplateServiceImpl.class); 
	
	@Autowired
	private MongoOperations mog; 
	
	@Override
	public Object persist(Object obj) {
		try{
			mog.save(obj);
			logger.trace("======AitMongoDBTemplateServiceImpl1111trace");  
			 logger.debug("======2AitMongoDBTemplateServiceImpl22debug");  
			 logger.info("======33AitMongoDBTemplateServiceImpl3info");  
			 logger.warn("======44AitMongoDBTemplateServiceImpl4warn");  
			 logger.error("======5AitMongoDBTemplateServiceImpl55error");
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Object update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
 
	
	
}
