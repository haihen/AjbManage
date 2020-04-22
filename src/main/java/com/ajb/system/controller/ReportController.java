package com.ajb.system.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajb.common.utils.R;
import com.ajb.system.domain.ReportDO;
import com.ajb.system.service.ReportService;

/**
 * InnoDB free: 11264 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-04 10:54:38
 */
 
@Controller
@RequestMapping("/sys/report")
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@GetMapping()
	@RequiresPermissions("sys:report:report")
	String Report(){
	    return "redirect:/chart/ajb_echarts.html";
	}
	
	@GetMapping("/list")
	@ResponseBody
	@RequiresPermissions("sys:report:report")
	public R list(ReportDO reportDo){
		Map<String,Object> reportMap = reportService.list(reportDo);
	    return R.ok().put("reportMap", reportMap);
	}
	
}
