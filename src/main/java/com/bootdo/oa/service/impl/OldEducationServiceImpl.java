package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.oa.dao.OldEducationDao;
import com.bootdo.oa.domain.OldEducationDO;
import com.bootdo.oa.service.OldEducationService;
import com.bootdo.system.domain.UserDO;



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
