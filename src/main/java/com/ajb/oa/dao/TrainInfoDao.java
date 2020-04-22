package com.ajb.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ajb.oa.domain.TrainInfoDO;

/**
 * InnoDB free: 7168 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
@Mapper
public interface TrainInfoDao {

	TrainInfoDO get(Integer id);
	
	List<TrainInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TrainInfoDO trainInfo);
	
	int update(TrainInfoDO trainInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
