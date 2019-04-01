package com.bootdo.oa.service;

import com.bootdo.oa.domain.OldEducationDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
public interface OldEducationService {
	
	OldEducationDO get(Integer id);
	
	OldEducationDO getAdjacent(Integer id);
	
	List<OldEducationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OldEducationDO oldEducation);
	
	int update(OldEducationDO oldEducation);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
