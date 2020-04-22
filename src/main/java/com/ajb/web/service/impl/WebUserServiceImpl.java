package com.ajb.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.common.redis.shiro.JedisUtils;
import com.ajb.common.utils.EncriptUtil;
import com.ajb.common.utils.R;
import com.ajb.common.utils.UserUtils;
import com.ajb.web.dao.WebUserDao;
import com.ajb.web.domain.WebUserDO;
import com.ajb.web.service.WebUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

@Service
public class WebUserServiceImpl implements WebUserService {
	@Autowired
	private WebUserDao userDao;
	
	@Override
	public WebUserDO get(Integer userId){
		return userDao.get(userId);
	}
	
	@Override
	public WebUserDO getByLogin(Map<String, Object> map){
		return userDao.getByLogin(map);
	}
	
	@Override
	public List<WebUserDO> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int countByLogin(Map<String, Object> map){
		return userDao.countByLogin(map);
	}
	
	@Override
	public int save(WebUserDO user){
		int ifSuccess = -1,count = 0;
		String loginName = "";
		Map<String, Object> map = new HashMap<String, Object>();
		loginName = user.getLoginName();
		map.put("loginName", loginName);
		count = userDao.count(map);
		if(count<=0){
			if("微信".equals(user.getInType())){
				user.setUserName("微信用户");
				user.setNickName("微信用户");
				user.setSex("保密");
				user.setIdCard("微信用户");
				user.setEmail("微信用户");
				user.setAddress("微信用户");
				user.setMemo("微信用户");
			} else if("APP".equals(user.getInType())){
				user.setMobile(loginName);
				user.setUserName("APP用户");
				user.setNickName("APP用户");
				user.setSex("保密");
				user.setIdCard("APP用户");
				user.setEmail("APP用户");
				user.setAddress("APP用户");
				user.setStreet("APP用户");
				user.setMemo("APP用户");
			} else if("APPDX".equals(user.getInType())){
				user.setMobile(loginName);
				user.setUserName("APPDX用户");
				user.setNickName("APPDX用户");
				user.setSex("保密");
				user.setIdCard("APPDX用户");
				user.setEmail("APPDX用户");
				user.setAddress("APPDX用户");
				user.setStreet("APPDX用户");
				user.setMemo("APPDX用户");
			}
			user.setPassword(EncriptUtil.MD5(user.getPassword()));
			ifSuccess = userDao.save(user);
		}
		return ifSuccess;
	}
	
	@Override
	public int update(WebUserDO user){
		user.setPassword(EncriptUtil.MD5(user.getPassword()));
		return userDao.update(user);
	}
	
	@Override
	public R updateMMWeb(WebUserDO user){
		int ifSuccess = -1,count = 0;
		String loginName = "",password = "",newPassword = "";
		Integer errNum = 0;
		errNum = UserUtils.getUserLockNum(user.getLoginName());
		if(errNum==-1){
			return R.error(2,"系统繁忙，请不要频繁操作！");
		}
		//登录错误或修改密码错误超过5次（不包含5次），账号被锁定
		if(errNum>=5){
			return R.error(2,"您的账户因多次操作错误已被锁定，锁定时长为1个小时！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		loginName = user.getLoginName();
		password = user.getPassword();
		password = EncriptUtil.MD5(password);
		user.setPassword(password);
		newPassword = user.getNewPassword();
		newPassword = EncriptUtil.MD5(newPassword);
		user.setNewPassword(newPassword);
		map.put("loginName", loginName);
		map.put("password", password);
		count = userDao.count(map);
		int code = 0;
		if(count>0){
			ifSuccess = userDao.updateMMWeb(user);
			code = UserUtils.setUserLockNum(loginName,true);
			if(code==-1){
				return R.error(2,"系统繁忙，请不要频繁操作！");
			}
			return R.ok();
		} else {
			code = UserUtils.setUserLockNum(loginName,false);
			if(code==-1){
				return R.error(2,"系统繁忙，请不要频繁操作！");
			}
		}
		return R.error(2,"修改个人密码失败！");
	}
	
	@Override
	public int updatePointsWeb(WebUserDO user){
		return userDao.updatePointsWeb(user);
	}
	
	@Override
	public int remove(Integer userId){
		return userDao.remove(userId);
	}
	
	@Override
	public int batchRemove(Integer[] userIds){
		return userDao.batchRemove(userIds);
	}
	
	@Override
	public void updateAllMM(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<WebUserDO> userList = userDao.list(map);
		for(WebUserDO user:userList){
			String pwd = user.getPassword();
			pwd = EncriptUtil.MD5(pwd);
			WebUserDO userNew = new WebUserDO();
			userNew.setNewPassword(pwd);
			userNew.setLoginName(user.getLoginName());
			userDao.updateMMWeb(userNew);
		}
	}
}
