package com.ait.mongodb.database.query;

import java.util.HashMap;
import java.util.Map;

public class QueryBean {
   
	private Object id;
	private Map<String, Object> query; 
	private Map<String, Boolean> order;
	
	public QueryBean(Object id){
		this.id = id;
		this.query = new HashMap<String, Object>();
		this.order = new HashMap<String, Boolean>();
	}

	public Map<String, Object> getQuery() {
		return query;
	}

	public Map<String, Boolean> getOrder() {
		return order;
	}

	public Object getId() {
		return id;
	}
	
	
	
}
