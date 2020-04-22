package com.ajb.ajbtype.dao;

import com.ajb.ajbtype.domain.AjbTypeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * InnoDB free: 10240 kB
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
@Mapper
public interface AjbTypeDao {

	AjbTypeDO get(Integer id);
	
	List<AjbTypeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AjbTypeDO home);
	
	int update(AjbTypeDO home);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
