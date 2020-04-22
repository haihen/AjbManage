package com.ajb.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.business.dao.BusinessDao;
import com.ajb.business.domain.BusinessDO;
import com.ajb.business.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {
	@Autowired
	private BusinessDao businessDao;
	
	@Override
	public BusinessDO get(Integer id){
		return businessDao.get(id);
	}
	
	@Override
	public List<BusinessDO> list(Map<String, Object> map){
		return businessDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return businessDao.count(map);
	}
	
	@Override
	public int save(BusinessDO business){
		return businessDao.save(business);
	}
	
	@Override
	public int update(BusinessDO business){
		return businessDao.update(business);
	}
	
	@Override
	public int remove(Integer id){
		return businessDao.remove(id);
	}
	
}
