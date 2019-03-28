package com.bootdo.oa.dao;

import com.bootdo.oa.domain.TrainTypeDO;

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
public interface TrainTypeDao {

	TrainTypeDO get(Integer id);
	
	List<TrainTypeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TrainTypeDO trainType);
	
	int update(TrainTypeDO trainType);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
