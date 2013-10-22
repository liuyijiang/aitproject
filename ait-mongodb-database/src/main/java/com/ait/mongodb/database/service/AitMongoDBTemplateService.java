package com.ait.mongodb.database.service;

import java.util.List;

import com.ait.mongodb.database.query.PageBean;
import com.ait.mongodb.database.query.QueryBean;
import com.ait.mongodb.database.query.UpdateBean;

public interface AitMongoDBTemplateService {
   
	public Object persist(Object obj);
	
	public boolean update(UpdateBean bean, Class<?> clzss);
	
	public boolean incUpdate(UpdateBean bean, Class<?> clzss);
	
	public <T> Object findOne(QueryBean bean, Class<T> clzss);
	
	public <T> List<T> find(QueryBean bean, PageBean page, Class<T> clzss);
	
	public <T> List<T> findRegex(QueryBean bean, PageBean page, Class<T> clzss);
	
	public boolean delete(Object obj);
	
}
