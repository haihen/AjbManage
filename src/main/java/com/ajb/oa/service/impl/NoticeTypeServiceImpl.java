package com.ajb.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.oa.dao.NoticeTypeDao;
import com.ajb.oa.domain.NoticeTypeDO;
import com.ajb.oa.service.NoticeTypeService;

import java.util.List;
import java.util.Map;



@Service
public class NoticeTypeServiceImpl implements NoticeTypeService {
	@Autowired
	private NoticeTypeDao noticeTypeDao;
	
	@Override
	public NoticeTypeDO get(Integer id){
		return noticeTypeDao.get(id);
	}
	
	@Override
	public List<NoticeTypeDO> list(Map<String, Object> map){
		return noticeTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return noticeTypeDao.count(map);
	}
	
	@Override
	public int save(NoticeTypeDO noticeType){
		return noticeTypeDao.save(noticeType);
	}
	
	@Override
	public int update(NoticeTypeDO noticeType){
		return noticeTypeDao.update(noticeType);
	}
	
	@Override
	public int remove(Integer id){
		return noticeTypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return noticeTypeDao.batchRemove(ids);
	}
	
}
