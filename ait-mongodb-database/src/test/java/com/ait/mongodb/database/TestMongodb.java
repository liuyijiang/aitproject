package com.ait.mongodb.database;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Sort;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 测试联通性
 * @author liuyijiang
 *
 */
public class TestMongodb {

	private MongoOperations mog; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "liuyijiang");
		map.put("age", 1);
		for(String key:map.keySet()){
			System.out.println(key+" _  "+map.get(key));
		}
	}
  
	
	public <T> boolean updateSingleObject(Object id,Map<String,Object> query, Map<String,Object> data, Class<T> clzss){
        try{
        	Criteria c = Criteria.where("id").is(id);
        	for(String key:query.keySet()){
    			c.and(key).is(query.get(key));
    		}
			Query q = new Query(c);
			Update u = new Update();
			for(String key:data.keySet()){
				u.set(key, data.get(key));
			}
			
        }catch(Exception e){
        	
        }
		return true;
	}
	
}
