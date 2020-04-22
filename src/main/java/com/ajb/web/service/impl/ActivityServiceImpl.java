package com.ajb.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.web.dao.ActivityDao;
import com.ajb.web.dao.WheelDao;
import com.ajb.web.domain.ActivityDO;
import com.ajb.web.domain.WheelDO;
import com.ajb.web.service.ActivityService;
import com.ajb.web.service.WheelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityDao activityDao;
	
	@Override
	public ActivityDO get(Integer id){
		ActivityDO activity = new ActivityDO();
		activity = activityDao.get(id);
		if(1==activity.getIsPlay()){
			activity.setIsPlayName("发布");
		} else {
			activity.setIsPlayName("未发布");
		}
		return activity;
	}
	
	@Override
	public List<ActivityDO> list(Map<String, Object> map){
		List<ActivityDO> aList = new ArrayList<ActivityDO>();
		aList = activityDao.list(map);
		for(ActivityDO act:aList){
			if(1==act.getIsPlay()){
				act.setIsPlayName("发布");
			} else {
				act.setIsPlayName("未发布");
			}
		}
		return aList;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return activityDao.count(map);
	}
	
	@Override
	public List<ActivityDO> listPlay(Map<String, Object> map){
		List<ActivityDO> aList = new ArrayList<ActivityDO>();
		aList = activityDao.listPlay(map);
		for(ActivityDO act:aList){
			if(1==act.getIsPlay()){
				act.setIsPlayName("发布");
			} else {
				act.setIsPlayName("未发布");
			}
		}
		return aList;
	}
	
	@Override
	public int countPlay(Map<String, Object> map){
		return activityDao.countPlay(map);
	}
	
	@Override
	public int save(ActivityDO activity){
		return activityDao.save(activity);
	}
	
	@Override
	public int update(ActivityDO activity){
		return activityDao.update(activity);
	}
	
	@Override
	public int display(ActivityDO activity){
		return activityDao.display(activity);
	}
	
	@Override
	public int remove(Integer id){
		return activityDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return activityDao.batchRemove(ids);
	}
	
}
