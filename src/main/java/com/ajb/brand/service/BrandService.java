package com.ajb.brand.service;

import java.util.List;
import java.util.Map;

import com.ajb.brand.domain.BrandDO;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface BrandService {
	
	BrandDO get(Integer id);
	
	List<BrandDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BrandDO brand);
	
	int update(BrandDO brand);
	
	int remove(Integer id);
}
