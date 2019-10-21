package com.bootdo.web.dao;

import com.bootdo.web.domain.WebUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * InnoDB free: 9216 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-06 16:12:07
 */
@Mapper
public interface WebUserDao {

	WebUserDO get(Integer userId);
	
	WebUserDO getByLogin(Map<String, Object> map);
	
	List<WebUserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int countByLogin(Map<String,Object> map);
	
	int save(WebUserDO user);
	
	int update(WebUserDO user);
	
	int updateMMWeb(WebUserDO user);
	
	int updatePointsWeb(WebUserDO user);
	
	int remove(Integer user_id);
	
	int batchRemove(Integer[] userIds);
}
