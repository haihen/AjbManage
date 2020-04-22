package com.ajb.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.common.utils.ShiroUtils;
import com.ajb.oa.dao.ActivityInfoDao;
import com.ajb.oa.domain.ActivityInfoDO;
import com.ajb.oa.domain.SchoolEducationInfoDO;
import com.ajb.oa.service.ActivityInfoService;
import com.ajb.system.domain.UserDO;

import java.util.List;
import java.util.Map;



@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {
	@Autowired
	private ActivityInfoDao activityInfoDao;
	
	@Override
	public ActivityInfoDO get(Integer id){
		return activityInfoDao.get(id);
	}
	
	@Override
	public ActivityInfoDO getAdjacent(Integer id){
		return activityInfoDao.getAdjacent(id);
	}
	
	@Override
	public List<ActivityInfoDO> list(Map<String, Object> map){
		return activityInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return activityInfoDao.count(map);
	}
	
	@Override
	public int save(ActivityInfoDO activityInfo){
		UserDO u = ShiroUtils.getUser();
		activityInfo.setCreateUser(u.getUsername());
		System.out.println("qwe:::"+activityInfo.getContext());
		return activityInfoDao.save(activityInfo);
	}
	
	@Override
	public int update(ActivityInfoDO activityInfo){
		return activityInfoDao.update(activityInfo);
	}
	
	@Override
	public int remove(Integer id){
		return activityInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return activityInfoDao.batchRemove(ids);
	}
	
}
