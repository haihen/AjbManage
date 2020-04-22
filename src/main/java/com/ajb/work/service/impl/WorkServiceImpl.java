package com.ajb.work.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.work.dao.WorkDao;
import com.ajb.work.domain.WorkDO;
import com.ajb.work.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {
	@Autowired
	private WorkDao workDao;
	
	@Override
	public WorkDO get(Integer id){
		return workDao.get(id);
	}
	
	@Override
	public List<WorkDO> list(Map<String, Object> map){
		return workDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return workDao.count(map);
	}
	
	@Override
	public List<WorkDO> getNoLimitList(Map<String, Object> map){
		return workDao.getNoLimitList(map);
	}
	
	@Override
	public int save(WorkDO work){
		return workDao.save(work);
	}
	
	@Override
	public int update(WorkDO work){
		return workDao.update(work);
	}
	
	@Override
	public int remove(Integer id){
		return workDao.remove(id);
	}
	
}
