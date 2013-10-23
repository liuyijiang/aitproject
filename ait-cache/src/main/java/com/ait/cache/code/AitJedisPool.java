package com.ait.cache.code;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class AitJedisPool {

	private JedisPool jedisPool;

	public AitJedisPool(String host, int prot) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(80000);
		config.setMaxIdle(101);
		config.setMaxWait(1000l);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		config.setTestWhileIdle(true);
		config.setNumTestsPerEvictionRun(10);
		config.setTimeBetweenEvictionRunsMillis(60000);
		jedisPool = new JedisPool(config, host, prot);
	}

	public Jedis getJedis() {
		Jedis jedis = jedisPool.getResource();
		jedis.getClient().setTimeoutInfinite();
		return jedis;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	
}
