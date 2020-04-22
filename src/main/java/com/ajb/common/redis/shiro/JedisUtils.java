package com.ajb.common.redis.shiro;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.ajb.common.utils.FileUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	private static Logger log = Logger.getLogger(JedisUtils.class);
	private static JedisPoolConfig jedisPoolConfig;
	private static JedisPool jedisPool = null;
	public static Properties properties;
	
	public static JedisPoolConfig getJedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		Properties prosRedis = FileUtil.getProps(JedisUtils.class, "redis.properties");
		//可用连接实例的最大数目
		config.setMaxTotal(Integer.parseInt(prosRedis.get("jedis.MAX_ACTIVE").toString()));
		//空闲连接实例的最大数目
		config.setMaxIdle(Integer.parseInt(prosRedis.get("jedis.MAX_IDLE").toString()));
		config.setMinIdle(100);
		//当池内没有返回对象时，最大等待时间
		config.setMaxWaitMillis(Integer.parseInt(prosRedis.get("jedis.MAX_WAIT").toString()));
		//当调用return Object方法时，是否进行有效性检查
		config.setTestOnBorrow(Boolean.parseBoolean(prosRedis.get("jedis.TEST_ON_BORROW").toString()));
		config.setTestWhileIdle(true);
		jedisPoolConfig = config;
		return config;
	}
	
	
	public static JedisPool getJedisPool(){
		Properties prosRedis = FileUtil.getProps(JedisUtils.class, "redis.properties");
		JedisPool config = null;
		String host = prosRedis.get("jedis.host").toString(); //ip
	    String port = prosRedis.get("jedis.port").toString(); //端口
	    String timeOut = prosRedis.get("jedis.TIMEOUT").toString(); 
	    String password = prosRedis.get("jedis.AUTH").toString(); //密码
	    
	    if(jedisPoolConfig == null){
	    	jedisPoolConfig = getJedisPoolConfig();
	    }
	    if(password != null && !password.equals("")){
	    	config = new JedisPool(jedisPoolConfig, host,Integer.parseInt(port), 
	    			Integer.parseInt(timeOut), password, 0);
	    }else{
	    	config = new JedisPool(jedisPoolConfig,host,
	    			Integer.parseInt(port), Integer.parseInt(timeOut));
	    }
	    
	    jedisPool = config;
	    return config;
	}
	
	
	public synchronized Jedis getJedis() {  
        Jedis jedis = null;  
        if(jedisPool == null){
        	jedisPool = getJedisPool();
        }
        log.info("jedisPool======"+jedisPool);
        if (jedis == null) {  
            jedis = jedisPool.getResource();  
        } 
        log.info("jedis======"+jedis);
        return jedis;  
    }  
	
	
	public synchronized Properties getProps(){
		if(properties == null){
			properties = FileUtil.getProps(JedisUtils.class, "redis.properties");
		}
		return properties;
	}
	
	/**
	 * 获取jedis的value值
	 * @param jedis
	 * @param select
	 * @param key
	 * @return
	 */
	public String getJedisStr(Jedis jedis,String select,String key){
		properties = getProps();
		jedis.select(Integer.parseInt(properties.get(select).toString()));
		String value = jedis.get(key);
		return value;
	}
	
	public synchronized void returnResource(Jedis jedis) {  
        if (jedis != null) {  
            jedisPool.returnResource(jedis);  
        }  
    }  
	
	/** 
     * Jedis对象出异常的时候，回收Jedis对象资源 
     *  
     * @param jedis 
     */  
    public synchronized void returnBrokenResource(Jedis jedis) {  
        if (jedis != null) {  
            jedisPool.returnBrokenResource(jedis);  
        }  
  
    }  
}
