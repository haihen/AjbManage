package com.ajb.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.web.dao.WheelDao;
import com.ajb.web.domain.WheelDO;
import com.ajb.web.service.WheelService;

import java.util.List;
import java.util.Map;



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
