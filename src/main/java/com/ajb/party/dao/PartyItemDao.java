package com.ajb.party.dao;

import com.ajb.party.domain.PartyItemDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * InnoDB free: 10240 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
@Mapper
public interface PartyItemDao {

	PartyItemDO get(Integer id);
	
	List<PartyItemDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PartyItemDO home);
	
	int update(PartyItemDO home);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
