package com.bootdo.oa.dao;

import com.bootdo.oa.domain.ActivityInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * InnoDB free: 7168 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
@Mapper
public interface ActivityInfoDao {

	ActivityInfoDO get(Integer id);
	
	ActivityInfoDO getAdjacent(Integer id);
	
	List<ActivityInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ActivityInfoDO activityInfo);
	
	int update(ActivityInfoDO activityInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
