package com.ajb.human.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ajb.human.domain.HumanDO;

/**
 * InnoDB free: 10240 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
@Mapper
public interface HumanDao {

	HumanDO get(Integer id);
	
	List<HumanDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(HumanDO human);
	
	int update(HumanDO human);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
