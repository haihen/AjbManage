package com.ajb.science.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.home.dao.HomeDao;
import com.ajb.home.service.HomeService;
import com.ajb.science.dao.ScienceDao;
import com.ajb.science.domain.ScienceDO;
import com.ajb.science.service.ScienceService;

@Service
public class ScienceServiceImpl implements ScienceService {
	@Autowired
	private ScienceDao scienceDao;
	
	@Override
	public ScienceDO get(Integer id){
		return scienceDao.get(id);
	}
	
	@Override
	public List<ScienceDO> list(Map<String, Object> map){
		return scienceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scienceDao.count(map);
	}
	
	@Override
	public int save(ScienceDO home){
		return scienceDao.save(home);
	}
	
	@Override
	public int update(ScienceDO home){
		return scienceDao.update(home);
	}
	
	@Override
	public int remove(Integer id){
		return scienceDao.remove(id);
	}
	
}
