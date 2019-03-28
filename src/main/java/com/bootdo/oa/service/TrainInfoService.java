package com.bootdo.oa.service;

import com.bootdo.oa.domain.TrainInfoDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
public interface TrainInfoService {
	
	TrainInfoDO get(Integer id);
	
	List<TrainInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TrainInfoDO trainInfo);
	
	int update(TrainInfoDO trainInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
