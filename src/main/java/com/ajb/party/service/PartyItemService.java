package com.ajb.party.service;

import com.ajb.party.domain.PartyItemDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface PartyItemService {
	
	PartyItemDO get(Integer id);
	
	List<PartyItemDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PartyItemDO partyItem);
	
	int update(PartyItemDO partyItem);
	
	int remove(Integer id);
}
