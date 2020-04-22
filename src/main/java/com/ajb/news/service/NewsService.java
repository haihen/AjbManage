package com.ajb.news.service;

import java.util.List;
import java.util.Map;

import com.ajb.news.domain.NewsDO;

/**
 * InnoDB free: 10240 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-01-13 19:11:29
 */
public interface NewsService {
	
	NewsDO get(Integer id);
	
	List<NewsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NewsDO news);
	
	int update(NewsDO news);
	
	int updateBrowseNum(Integer id);
	
	int remove(Integer id);
}
