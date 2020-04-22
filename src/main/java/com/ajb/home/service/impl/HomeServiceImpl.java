package com.ajb.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.home.dao.HomeDao;
import com.ajb.home.domain.HomeDO;
import com.ajb.home.service.HomeService;



@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	private HomeDao homeDao;
	
	@Override
	public HomeDO get(Integer id){
		return homeDao.get(id);
	}
	
	@Override
	public List<HomeDO> list(Map<String, Object> map){
		return homeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return homeDao.count(map);
	}
	
	@Override
	public int save(HomeDO home){
		return homeDao.save(home);
	}
	
	@Override
	public int update(HomeDO home){
		return homeDao.update(home);
	}
	
	@Override
	public int remove(Integer id){
		return homeDao.remove(id);
	}
	
}
