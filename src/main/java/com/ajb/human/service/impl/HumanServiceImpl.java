package com.ajb.human.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.human.dao.HumanDao;
import com.ajb.human.domain.HumanDO;
import com.ajb.human.service.HumanService;

@Service
public class HumanServiceImpl implements HumanService {
	@Autowired
	private HumanDao humanDao;
	
	@Override
	public HumanDO get(Integer id){
		return humanDao.get(id);
	}
	
	@Override
	public List<HumanDO> list(Map<String, Object> map){
		return humanDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return humanDao.count(map);
	}
	
	@Override
	public int save(HumanDO human){
		return humanDao.save(human);
	}
	
	@Override
	public int update(HumanDO human){
		return humanDao.update(human);
	}
	
	@Override
	public int remove(Integer id){
		return humanDao.remove(id);
	}
	
}
