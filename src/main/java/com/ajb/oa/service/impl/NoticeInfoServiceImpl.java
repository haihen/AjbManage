package com.ajb.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.common.utils.ShiroUtils;
import com.ajb.oa.dao.NoticeInfoDao;
import com.ajb.oa.domain.ActivityInfoDO;
import com.ajb.oa.domain.NoticeInfoDO;
import com.ajb.oa.service.NoticeInfoService;
import com.ajb.system.domain.UserDO;

import java.util.List;
import java.util.Map;



@Service
public class NoticeInfoServiceImpl implements NoticeInfoService {
	@Autowired
	private NoticeInfoDao noticeInfoDao;
	
	@Override
	public NoticeInfoDO get(Integer id){
		return noticeInfoDao.get(id);
	}
	
	@Override
	public NoticeInfoDO getAdjacent(Integer id){
		return noticeInfoDao.getAdjacent(id);
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
