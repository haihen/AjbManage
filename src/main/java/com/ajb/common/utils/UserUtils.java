package com.ajb.common.utils;

import org.apache.http.util.TextUtils;

import com.ajb.common.redis.shiro.JedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class UserUtils {

	public static int getUserLockNum(String loginName){
		JedisUtils jedisUtils = new JedisUtils();
		Jedis jedis = null;
		String errNumStr = "0";
		Integer errNum = 0;
		try{
			jedis = jedisUtils.getJedis();
			errNumStr = jedis.get("LOCK"+loginName);
			if(TextUtils.isEmpty(errNumStr)){
				errNum = 0;
			} else {
				errNum = Integer.parseInt(errNumStr);
			}
		} catch (Exception e) { 
			if(jedis!=null){
				jedisUtils.returnBrokenResource(jedis);  
			}

			return -1;
		}finally{
			if(jedis!=null){
				jedisUtils.returnResource(jedis); 
			}
		}

		return errNum;
	}
	
	public static int setUserLockNum(String loginName,Boolean isDelKey){
		JedisUtils jedisUtils = new JedisUtils();
		Jedis jedis = null;
		
		String errNumStr = "0";
		Integer errNum = 0;
		
		try{
			jedis = jedisUtils.getJedis();
			System.out.println("loginName:::::::"+loginName);
			if(isDelKey){
				jedis.del("LOCK"+loginName);
			} else {
				errNumStr = jedis.get("LOCK"+loginName);
				if(TextUtils.isEmpty(errNumStr)){
					errNum = 0;
				} else {
					errNum = Integer.parseInt(errNumStr);
				}
				
				//登录错误+1
				errNum += 1;
				jedis.set("LOCK"+loginName, errNum+"");
				//登录错误或修改密码错误时，30分钟内不能大于等于5次
				if(errNum==1){
					jedis.expire("LOCK"+loginName, 30*60);
				}
				//登录错误或修改密码错误等于5次时，账户锁定一小时
				if(errNum==5){
					jedis.expire("LOCK"+loginName, 60*60);
				}
			}
		} catch (Exception e) { 
			if(jedis!=null){
				jedisUtils.returnBrokenResource(jedis);  
			}

			return -1;
		}finally{
			if(jedis!=null){
				jedisUtils.returnResource(jedis); 
			}
		}
		
		return 0;
	}
}
