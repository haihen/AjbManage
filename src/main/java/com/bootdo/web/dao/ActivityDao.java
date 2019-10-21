package com.bootdo.web.dao;

import com.bootdo.web.domain.ActivityDO;
import com.bootdo.web.domain.WheelDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * InnoDB free: 9216 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-05 09:17:50
 */
@Mapper
public interface ActivityDao {

	ActivityDO get(Integer id);
	
	List<ActivityDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	List<ActivityDO> listPlay(Map<String,Object> map);
	
	int countPlay(Map<String,Object> map);
	
	int save(ActivityDO activity);
	
	int update(ActivityDO activity);
	
	int display(ActivityDO activity);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
