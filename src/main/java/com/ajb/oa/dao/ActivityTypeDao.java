package com.ajb.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ajb.oa.domain.ActivityTypeDO;

/**
 * InnoDB free: 7168 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
@Mapper
public interface ActivityTypeDao {

	ActivityTypeDO get(Integer id);
	
	List<ActivityTypeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ActivityTypeDO activityType);
	
	int update(ActivityTypeDO activityType);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
