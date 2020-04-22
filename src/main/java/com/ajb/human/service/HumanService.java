package com.ajb.human.service;

import com.ajb.human.domain.HumanDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface HumanService {
	
	HumanDO get(Integer id);
	
	List<HumanDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HumanDO human);
	
	int update(HumanDO human);
	
	int remove(Integer id);
}
