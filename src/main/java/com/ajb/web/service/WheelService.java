package com.ajb.web.service;

import java.util.List;
import java.util.Map;

import com.ajb.web.domain.WheelDO;

/**
 * InnoDB free: 9216 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-05 09:17:50
 */
public interface WheelService {
	
	WheelDO get(Integer id);
	
	List<WheelDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WheelDO wheel);
	
	int update(WheelDO wheel);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
