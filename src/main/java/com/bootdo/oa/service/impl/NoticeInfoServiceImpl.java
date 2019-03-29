package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.oa.dao.NoticeInfoDao;
import com.bootdo.oa.domain.NoticeInfoDO;
import com.bootdo.oa.service.NoticeInfoService;
import com.bootdo.system.domain.UserDO;



@Service
public class NoticeInfoServiceImpl implements NoticeInfoService {
	@Autowired
	private NoticeInfoDao noticeInfoDao;
	
	@Override
	public NoticeInfoDO get(Integer id){
		return noticeInfoDao.get(id);
	}
	
	@Override
	public List<NoticeInfoDO> list(Map<String, Object> map){
		return noticeInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return noticeInfoDao.count(map);
	}
	
	@Override
	public int save(NoticeInfoDO noticeInfo){
		UserDO u = ShiroUtils.getUser();
		noticeInfo.setCreateUser(u.getUsername());
		return noticeInfoDao.save(noticeInfo);
	}
	
	@Override
	public int update(NoticeInfoDO noticeInfo){
		return noticeInfoDao.update(noticeInfo);
	}
	
	@Override
	public int remove(Integer id){
		return noticeInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return noticeInfoDao.batchRemove(ids);
	}
	
}
