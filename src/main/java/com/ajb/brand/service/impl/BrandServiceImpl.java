package com.ajb.brand.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ajb.brand.service.BrandService;
import com.ajb.brand.dao.BrandDao;
import com.ajb.brand.domain.BrandDO;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandDao brandDao;
	
	@Override
	public BrandDO get(Integer id){
		return brandDao.get(id);
	}
	
	@Override
	public List<BrandDO> list(Map<String, Object> map){
		return brandDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return brandDao.count(map);
	}
	
	@Override
	public int save(BrandDO brand){
		return brandDao.save(brand);
	}
	
	@Override
	public int update(BrandDO brand){
		return brandDao.update(brand);
	}
	
	@Override
	public int remove(Integer id){
		return brandDao.remove(id);
	}
	
}
