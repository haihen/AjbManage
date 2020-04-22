package com.ajb.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.common.utils.ShiroUtils;
import com.ajb.oa.dao.TrainInfoDao;
import com.ajb.oa.domain.TrainInfoDO;
import com.ajb.oa.service.TrainInfoService;
import com.ajb.system.domain.UserDO;

import java.util.List;
import java.util.Map;



@Service
public class TrainInfoServiceImpl implements TrainInfoService {
	@Autowired
	private TrainInfoDao trainInfoDao;
	
	@Override
	public TrainInfoDO get(Integer id){
		return trainInfoDao.get(id);
	}
	
	@Override
	public List<TrainInfoDO> list(Map<String, Object> map){
		return trainInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return trainInfoDao.count(map);
	}
	
	@Override
	public int save(TrainInfoDO trainInfo){
		UserDO u = ShiroUtils.getUser();
		trainInfo.setCreateUser(u.getUsername());
		return trainInfoDao.save(trainInfo);
	}
	
	@Override
	public int update(TrainInfoDO trainInfo){
		return trainInfoDao.update(trainInfo);
	}
	
	@Override
	public int remove(Integer id){
		return trainInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return trainInfoDao.batchRemove(ids);
	}
	
}
