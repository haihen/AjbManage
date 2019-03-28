package com.bootdo.oa.service;

import com.bootdo.oa.domain.TrainTypeDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
public interface TrainTypeService {
	
	TrainTypeDO get(Integer id);
	
	List<TrainTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TrainTypeDO trainType);
	
	int update(TrainTypeDO trainType);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
