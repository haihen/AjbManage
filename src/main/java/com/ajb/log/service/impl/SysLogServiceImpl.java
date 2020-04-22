package com.ajb.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajb.log.SysLog;
import com.ajb.log.dao.SysLogDao;
import com.ajb.log.service.SysLogService;



@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public int save(SysLog sysLog) {
		return sysLogDao.save(sysLog);
	}
}
