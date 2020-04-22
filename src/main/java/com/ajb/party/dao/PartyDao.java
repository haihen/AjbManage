package com.ajb.party.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ajb.party.domain.PartyDO;

/**
 * InnoDB free: 10240 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
@Mapper
public interface PartyDao {

	PartyDO get(Integer id);
	
	List<PartyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PartyDO party);
	
	int update(PartyDO party);
	
	int updateBrowseNum(Integer id);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
