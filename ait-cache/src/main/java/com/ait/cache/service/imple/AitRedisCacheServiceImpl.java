package com.ait.cache.service.imple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.ait.cache.code.AitJedisPool;
import com.ait.cache.service.AitRedisCacheService;
import com.alibaba.fastjson.JSON;

@Service
public class AitRedisCacheServiceImpl implements AitRedisCacheService {

	public static final Logger logger = LoggerFactory.getLogger(AitRedisCacheServiceImpl.class);
	
	@Autowired(required = true)
	public AitJedisPool jedisPool;
	
	public <T> T getData(String key, Class<T> clzss) {
		Jedis jedis = null;
		T obj = null;
		try{
			jedis = jedisPool.getJedis();
			String join = jedis.get(key);
			obj = JSON.parseObject(join, clzss);
		}catch(Exception e){
			jedis.disconnect(); 
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
		}finally{
		   jedisPool.getJedisPool().returnResource(jedis);
		}
		return obj;
	}

	public boolean setData(String key, Object obj) {
		Jedis jedis = null;
		try{
			jedis = jedisPool.getJedis();
			String json = JSON.toJSONString(obj);
			jedis.set(key, json);
		}catch(Exception e){
			jedis.disconnect(); 
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
			return false;
		}finally{
		   jedisPool.getJedisPool().returnResource(jedis);
		}
		return true;
	}

}
