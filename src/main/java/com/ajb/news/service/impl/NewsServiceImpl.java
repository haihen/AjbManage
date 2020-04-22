package com.ajb.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.news.dao.NewsDao;
import com.ajb.news.domain.NewsDO;
import com.ajb.news.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsDao newsDao;
	
	@Override
	public NewsDO get(Integer id){
		return newsDao.get(id);
	}
	
	@Override
	public List<NewsDO> list(Map<String, Object> map){
		return newsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return newsDao.count(map);
	}
	
	@Override
	public int save(NewsDO news){
		return newsDao.save(news);
	}
	
	@Override
	public int update(NewsDO news){
		return newsDao.update(news);
	}
	
	@Override
	public int updateBrowseNum(Integer id){
		return newsDao.updateBrowseNum(id);
	}
	
	@Override
	public int remove(Integer id){
		return newsDao.remove(id);
	}
	
}
