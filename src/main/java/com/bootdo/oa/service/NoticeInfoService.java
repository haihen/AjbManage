package com.bootdo.oa.service;

import com.bootdo.oa.domain.NoticeInfoDO;

import java.util.List;
import java.util.Map;

/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
public interface NoticeInfoService {
	
	NoticeInfoDO get(Integer id);
	
	List<NoticeInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NoticeInfoDO noticeInfo);
	
	int update(NoticeInfoDO noticeInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
