package com.ajb.home.service;

import com.ajb.home.domain.HomeDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface HomeService {
	
	HomeDO get(Integer id);
	
	List<HomeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HomeDO home);
	
	int update(HomeDO home);
	
	int remove(Integer id);
}
