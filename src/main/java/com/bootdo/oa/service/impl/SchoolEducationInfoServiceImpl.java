package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.oa.dao.SchoolEducationInfoDao;
import com.bootdo.oa.domain.SchoolEducationInfoDO;
import com.bootdo.oa.service.SchoolEducationInfoService;
import com.bootdo.system.domain.UserDO;



@Service
public class SchoolEducationInfoServiceImpl implements SchoolEducationInfoService {
	@Autowired
	private SchoolEducationInfoDao schoolEducationInfoDao;
	
	@Override
	public SchoolEducationInfoDO get(Integer id){
		return schoolEducationInfoDao.get(id);
	}
	
	@Override
	public List<SchoolEducationInfoDO> list(Map<String, Object> map){
		return schoolEducationInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolEducationInfoDao.count(map);
	}
	
	@Override
	public int save(SchoolEducationInfoDO schoolEducationInfo){
		UserDO u = ShiroUtils.getUser();
		schoolEducationInfo.setCreateUser(u.getUsername());
		return schoolEducationInfoDao.save(schoolEducationInfo);
	}
	
	@Override
	public int update(SchoolEducationInfoDO schoolEducationInfo){
		return schoolEducationInfoDao.update(schoolEducationInfo);
	}
	
	@Override
	public int remove(Integer id){
		return schoolEducationInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return schoolEducationInfoDao.batchRemove(ids);
	}
	
}
