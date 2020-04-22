package com.ajb.work.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ajb.work.domain.WorkDO;

/**
 * InnoDB free: 10240 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
@Mapper
public interface WorkDao {

	WorkDO get(Integer id);
	
	List<WorkDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	List<WorkDO> getNoLimitList(Map<String,Object> map);
	
	int save(WorkDO work);
	
	int update(WorkDO work);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
