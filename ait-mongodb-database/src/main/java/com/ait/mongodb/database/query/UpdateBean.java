package com.ait.mongodb.database.query;

import java.util.HashMap;
import java.util.Map;

public class UpdateBean {
   
	private Object id;
	private Map<String, Object> query; 
	private Map<String, Object> data;
	
	public UpdateBean(Object id){
		this.id = id;
		this.query = new HashMap<String, Object>();
		this.data = new HashMap<String, Object>();
	}

	public Object getId() {
		return id;
	}

	public Map<String, Object> getQuery() {
		return query;
	}

	public Map<String, Object> getData() {
		return data;
	}
	
	
	
}
