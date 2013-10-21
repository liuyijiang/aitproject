package com.ait.mongodb.database.service;

public interface AitMongoDBTemplateService {
   
	public Object persist(Object obj);
	
	public Object update(Object obj);
	
	public boolean delete(Object obj);
	
}
