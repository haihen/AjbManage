package com.ajb.work.service;

import com.ajb.work.domain.WorkDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface WorkService {
	
	WorkDO get(Integer id);
	
	List<WorkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	List<WorkDO> getNoLimitList(Map<String, Object> map);
	
	int save(WorkDO work);
	
	int update(WorkDO work);
	
	int remove(Integer id);
}
