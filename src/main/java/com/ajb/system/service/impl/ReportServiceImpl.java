package com.ajb.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajb.log.SysLog;
import com.ajb.log.dao.SysLogDao;
import com.ajb.system.domain.ReportDO;
import com.ajb.system.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public Map<String, Object> list(ReportDO reportDo) {
		Map<String,Object> reportList = new HashMap<String,Object>();
		
		String[] moduleArray = {"首页","新闻中心","党的建设","业务领域","科技创新","品牌文化","人力资源","商业合作"};
		Integer[] moduleNumArray = {0,0,0,0,0,0,0,0};

		Map<String,Object> map = new HashMap<String,Object>();
		List<SysLog> stList = sysLogDao.list(map);
		
		Integer moduleNum=0;
		
		for(SysLog sysLog:stList){
			String moduleName = "";
			if(!TextUtils.isEmpty(sysLog.getOperation())){
				moduleName = sysLog.getOperation();
			}
			
			for(int i=0;i<moduleArray.length;i++){
				if(moduleName.indexOf(moduleArray[i])>-1){
					moduleNum = Integer.parseInt(moduleNumArray[i]+"")+1;
					moduleNumArray[i] = moduleNum;
				}
			}
		}
		
		reportList.put("moduleArray", moduleArray);
		reportList.put("moduleNumArray", moduleNumArray);
		
		return reportList;
	}
	
}