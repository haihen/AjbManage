package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.ActivityTypeDao;
import com.bootdo.oa.domain.ActivityTypeDO;
import com.bootdo.oa.service.ActivityTypeService;



@Service
public class ActivityTypeServiceImpl implements ActivityTypeService {
	@Autowired
	private ActivityTypeDao activityTypeDao;
	
	@Override
	public ActivityTypeDO get(Integer id){
		return activityTypeDao.get(id);
	}
	
	@Override
	public List<ActivityTypeDO> list(Map<String, Object> map){
		return activityTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return activityTypeDao.count(map);
	}
	
	@Override
	public int save(ActivityTypeDO activityType){
		return activityTypeDao.save(activityType);
	}
	
	@Override
	public int update(ActivityTypeDO activityType){
		return activityTypeDao.update(activityType);
	}
	
	@Override
	public int remove(Integer id){
		return activityTypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return activityTypeDao.batchRemove(ids);
	}
	
}
