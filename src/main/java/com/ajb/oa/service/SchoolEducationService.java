package com.ajb.oa.service;

import java.util.List;
import java.util.Map;

import com.ajb.oa.domain.SchoolEducationDO;

/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
public interface SchoolEducationService {
	
	SchoolEducationDO get(Integer id);
	
	List<SchoolEducationDO> list(Map<String, Object> map);
	
	List<SchoolEducationDO> list2(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolEducationDO schoolEducation);
	
	int update(SchoolEducationDO schoolEducation);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
