package com.ajb.science.service;

import java.util.List;
import java.util.Map;

import com.ajb.science.domain.ScienceDO;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface ScienceService {
	
	ScienceDO get(Integer id);
	
	List<ScienceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScienceDO science);
	
	int update(ScienceDO science);
	
	int remove(Integer id);
}
