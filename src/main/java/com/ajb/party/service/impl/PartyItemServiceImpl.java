package com.ajb.party.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.party.dao.PartyItemDao;
import com.ajb.party.domain.PartyItemDO;
import com.ajb.party.service.PartyItemService;

import java.util.List;
import java.util.Map;

@Service
public class PartyItemServiceImpl implements PartyItemService {
	@Autowired
	private PartyItemDao partyItemDao;
	
	@Override
	public PartyItemDO get(Integer id){
		return partyItemDao.get(id);
	}
	
	@Override
	public List<PartyItemDO> list(Map<String, Object> map){
		return partyItemDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return partyItemDao.count(map);
	}
	
	@Override
	public int save(PartyItemDO home){
		return partyItemDao.save(home);
	}
	
	@Override
	public int update(PartyItemDO home){
		return partyItemDao.update(home);
	}
	
	@Override
	public int remove(Integer id){
		return partyItemDao.remove(id);
	}
	
}
