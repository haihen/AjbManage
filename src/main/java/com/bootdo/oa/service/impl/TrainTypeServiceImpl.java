package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.TrainTypeDao;
import com.bootdo.oa.domain.TrainTypeDO;
import com.bootdo.oa.service.TrainTypeService;



@Service
public class TrainTypeServiceImpl implements TrainTypeService {
	@Autowired
	private TrainTypeDao trainTypeDao;
	
	@Override
	public TrainTypeDO get(Integer id){
		return trainTypeDao.get(id);
	}
	
	@Override
	public List<TrainTypeDO> list(Map<String, Object> map){
		return trainTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return trainTypeDao.count(map);
	}
	
	@Override
	public int save(TrainTypeDO trainType){
		return trainTypeDao.save(trainType);
	}
	
	@Override
	public int update(TrainTypeDO trainType){
		return trainTypeDao.update(trainType);
	}
	
	@Override
	public int remove(Integer id){
		return trainTypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return trainTypeDao.batchRemove(ids);
	}
	
}
