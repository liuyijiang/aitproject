package com.ait.cache.service;

public interface AitRedisCacheService {
   
    public <T> T getData(String key,Class<T> clzss);
	
	public boolean setData(String key, Object obj);
	 
}
