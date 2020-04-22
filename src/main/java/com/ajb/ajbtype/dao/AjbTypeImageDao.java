package com.ajb.ajbtype.dao;

import com.ajb.ajbtype.domain.AjbTypeImageDO;

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
public interface AjbTypeImageDao {

	AjbTypeImageDO get(Integer id);
	
	List<AjbTypeImageDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AjbTypeImageDO ajbTypeImage);
	
	int update(AjbTypeImageDO ajbTypeImage);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
