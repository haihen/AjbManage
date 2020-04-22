package com.ajb.ajbtype.service;

import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.ajbtype.domain.AjbTypeImageDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface AjbTypeImageService {
	
	AjbTypeImageDO get(Integer id);
	
	List<AjbTypeImageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AjbTypeImageDO ajbTypeImage);
	
	int update(AjbTypeImageDO ajbTypeImage);
	
	int remove(Integer id);
}
