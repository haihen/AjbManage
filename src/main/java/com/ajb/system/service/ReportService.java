package com.ajb.system.service;

import java.util.Map;

import com.ajb.system.domain.ReportDO;

public interface ReportService {

	Map<String,Object> list(ReportDO reportDo);
	
}
