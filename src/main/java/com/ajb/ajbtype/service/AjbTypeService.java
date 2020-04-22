package com.ajb.ajbtype.service;

import com.ajb.ajbtype.domain.AjbTypeDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface AjbTypeService {
	
	AjbTypeDO get(Integer id);
	
	List<AjbTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AjbTypeDO ajbtype);
	
	int update(AjbTypeDO ajbtype);
	
	int remove(Integer id);
}
