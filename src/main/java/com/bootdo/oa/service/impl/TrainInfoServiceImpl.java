package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.TrainInfoDao;
import com.bootdo.oa.domain.TrainInfoDO;
import com.bootdo.oa.service.TrainInfoService;



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
