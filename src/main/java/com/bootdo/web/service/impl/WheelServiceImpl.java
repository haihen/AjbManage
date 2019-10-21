package com.bootdo.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.web.dao.WheelDao;
import com.bootdo.web.domain.WheelDO;
import com.bootdo.web.service.WheelService;



@Service
public class WheelServiceImpl implements WheelService {
	@Autowired
	private WheelDao wheelDao;
	
	@Override
	public WheelDO get(Integer id){
		return wheelDao.get(id);
	}
	
	@Override
	public List<WheelDO> list(Map<String, Object> map){
		return wheelDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return wheelDao.count(map);
	}
	
	@Override
	public int save(WheelDO wheel){
		return wheelDao.save(wheel);
	}
	
	@Override
	public int update(WheelDO wheel){
		return wheelDao.update(wheel);
	}
	
	@Override
	public int remove(Integer id){
		return wheelDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return wheelDao.batchRemove(ids);
	}
	
}
