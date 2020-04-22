package com.ajb.web.service;

import java.util.List;
import java.util.Map;

import com.ajb.web.domain.ActivityDO;
import com.ajb.web.domain.WheelDO;

/**
 * InnoDB free: 9216 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-05 09:17:50
 */
public interface ActivityService {
	
	ActivityDO get(Integer id);
	
	List<ActivityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	List<ActivityDO> listPlay(Map<String, Object> map);
	
	int countPlay(Map<String, Object> map);
	
	int save(ActivityDO activity);
	
	int update(ActivityDO activity);
	
	int display(ActivityDO activity);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
