package com.ajb.business.service;

import java.util.List;
import java.util.Map;

import com.ajb.business.domain.BusinessDO;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface BusinessService {
	
	BusinessDO get(Integer id);
	
	List<BusinessDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BusinessDO business);
	
	int update(BusinessDO business);
	
	int remove(Integer id);
}
