package com.ajb.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.oa.dao.SchoolEducationDao;
import com.ajb.oa.domain.SchoolEducationDO;
import com.ajb.oa.service.SchoolEducationService;

import java.util.List;
import java.util.Map;



@Service
public class SchoolEducationServiceImpl implements SchoolEducationService {
	@Autowired
	private SchoolEducationDao schoolEducationDao;
	
	@Override
	public SchoolEducationDO get(Integer id){
		return schoolEducationDao.get(id);
	}
	
	@Override
	public List<SchoolEducationDO> list(Map<String, Object> map){
		return schoolEducationDao.list(map);
	}
	
	@Override
	public List<SchoolEducationDO> list2(Map<String, Object> map){
		return schoolEducationDao.list2(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolEducationDao.count(map);
	}
	
	@Override
	public int save(SchoolEducationDO schoolEducation){
		return schoolEducationDao.save(schoolEducation);
	}
	
	@Override
	public int update(SchoolEducationDO schoolEducation){
		return schoolEducationDao.update(schoolEducation);
	}
	
	@Override
	public int remove(Integer id){
		return schoolEducationDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return schoolEducationDao.batchRemove(ids);
	}
	
}
