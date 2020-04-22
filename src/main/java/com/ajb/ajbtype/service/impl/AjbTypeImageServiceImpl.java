package com.ajb.ajbtype.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.ajbtype.dao.AjbTypeDao;
import com.ajb.ajbtype.dao.AjbTypeImageDao;
import com.ajb.ajbtype.service.AjbTypeImageService;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.home.dao.HomeDao;
import com.ajb.ajbtype.domain.AjbTypeImageDO;
import com.ajb.home.service.HomeService;

@Service
public class AjbTypeImageServiceImpl implements AjbTypeImageService {
	@Autowired
	private AjbTypeImageDao ajbTypeImageDao;
	
	@Override
	public AjbTypeImageDO get(Integer id){
		return ajbTypeImageDao.get(id);
	}
	
	@Override
	public List<AjbTypeImageDO> list(Map<String, Object> map){
		return ajbTypeImageDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ajbTypeImageDao.count(map);
	}
	
	@Override
	public int save(AjbTypeImageDO home){
		return ajbTypeImageDao.save(home);
	}
	
	@Override
	public int update(AjbTypeImageDO home){
		return ajbTypeImageDao.update(home);
	}
	
	@Override
	public int remove(Integer id){
		return ajbTypeImageDao.remove(id);
	}
	
}
