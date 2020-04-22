package com.ajb.ajbtype.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.ajbtype.dao.AjbTypeDao;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.home.dao.HomeDao;
import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.home.service.HomeService;

@Service
public class AjbTypeServiceImpl implements AjbTypeService {
	@Autowired
	private AjbTypeDao ajbTypeDao;
	
	@Override
	public AjbTypeDO get(Integer id){
		return ajbTypeDao.get(id);
	}
	
	@Override
	public List<AjbTypeDO> list(Map<String, Object> map){
		return ajbTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ajbTypeDao.count(map);
	}
	
	@Override
	public int save(AjbTypeDO home){
		return ajbTypeDao.save(home);
	}
	
	@Override
	public int update(AjbTypeDO home){
		return ajbTypeDao.update(home);
	}
	
	@Override
	public int remove(Integer id){
		return ajbTypeDao.remove(id);
	}
	
}
