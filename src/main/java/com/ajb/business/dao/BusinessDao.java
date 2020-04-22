package com.ajb.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ajb.business.domain.BusinessDO;

/**
 * InnoDB free: 10240 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
@Mapper
public interface BusinessDao {

	BusinessDO get(Integer id);
	
	List<BusinessDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BusinessDO business);
	
	int update(BusinessDO business);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
