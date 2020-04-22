package com.ajb.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.common.utils.ShiroUtils;
import com.ajb.oa.dao.OldEducationDao;
import com.ajb.oa.domain.OldEducationDO;
import com.ajb.oa.service.OldEducationService;
import com.ajb.system.domain.UserDO;

import java.util.List;
import java.util.Map;



@Service
public class OldEducationServiceImpl implements OldEducationService {
	@Autowired
	private OldEducationDao oldEducationDao;
	
	@Override
	public OldEducationDO get(Integer id){
		return oldEducationDao.get(id);
	}
	
	@Override
	public OldEducationDO getAdjacent(Integer id){
		return oldEducationDao.getAdjacent(id);
	}
	
	@Override
	public List<OldEducationDO> list(Map<String, Object> map){
		return oldEducationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oldEducationDao.count(map);
	}
	
	@Override
	public int save(OldEducationDO oldEducation){
		UserDO u = ShiroUtils.getUser();
		oldEducation.setCreateUser(u.getUsername());
		return oldEducationDao.save(oldEducation);
	}
	
	@Override
	public int update(OldEducationDO oldEducation){
		return oldEducationDao.update(oldEducation);
	}
	
	@Override
	public int remove(Integer id){
		return oldEducationDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oldEducationDao.batchRemove(ids);
	}
	
}
