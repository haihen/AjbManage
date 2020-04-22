package com.ajb.party.service;

import java.util.List;
import java.util.Map;

import com.ajb.party.domain.PartyDO;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface PartyService {
	
	PartyDO get(Integer id);
	
	List<PartyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PartyDO party);
	
	int update(PartyDO party);
	
	int updateBrowseNum(Integer id);
	
	int remove(Integer id);
}
