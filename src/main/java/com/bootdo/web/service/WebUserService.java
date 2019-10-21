package com.bootdo.web.service;

import com.bootdo.common.utils.R;
import com.bootdo.web.domain.WebUserDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 9216 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-06 16:12:07
 */
public interface WebUserService {
	
	WebUserDO get(Integer userId);
	
	WebUserDO getByLogin(Map<String, Object> map);
	
	List<WebUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int countByLogin(Map<String, Object> map);
	
	int save(WebUserDO user);
	
	int update(WebUserDO user);
	
	R updateMMWeb(WebUserDO user);
	
	void updateAllMM();
	
	int updatePointsWeb(WebUserDO user);
	
	int remove(Integer userId);
	
	int batchRemove(Integer[] userIds);
}
