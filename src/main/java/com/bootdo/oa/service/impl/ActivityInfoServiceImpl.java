package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.oa.dao.ActivityInfoDao;
import com.bootdo.oa.domain.ActivityInfoDO;
import com.bootdo.oa.service.ActivityInfoService;
import com.bootdo.system.domain.UserDO;



@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {
	@Autowired
	private ActivityInfoDao activityInfoDao;
	
	@Override
	public ActivityInfoDO get(Integer id){
		return activityInfoDao.get(id);
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
