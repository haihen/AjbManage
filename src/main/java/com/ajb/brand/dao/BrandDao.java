package com.ajb.brand.dao;

import com.ajb.brand.domain.BrandDO;

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
public interface BrandDao {

	BrandDO get(Integer id);
	
	List<BrandDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BrandDO brand);
	
	int update(BrandDO brand);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
