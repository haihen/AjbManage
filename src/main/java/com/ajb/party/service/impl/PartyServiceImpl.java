package com.ajb.party.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.party.dao.PartyDao;
import com.ajb.party.domain.PartyDO;
import com.ajb.party.service.PartyService;

@Service
public class PartyServiceImpl implements PartyService {
	@Autowired
	private PartyDao partyDao;
	
	@Override
	public PartyDO get(Integer id){
		return partyDao.get(id);
	}
	
	@Override
	public List<PartyDO> list(Map<String, Object> map){
		return partyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return partyDao.count(map);
	}
	
	@Override
	public int save(PartyDO party){
		return partyDao.save(party);
	}
	
	@Override
	public int update(PartyDO party){
		return partyDao.update(party);
	}
	
	@Override
	public int updateBrowseNum(Integer id){
		return partyDao.updateBrowseNum(id);
	}
	
	@Override
	public int remove(Integer id){
		return partyDao.remove(id);
	}
	
}
