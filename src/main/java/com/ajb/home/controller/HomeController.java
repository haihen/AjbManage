package com.ajb.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajb.home.domain.HomeDO;
import com.ajb.home.service.HomeService;
import com.ajb.log.MyLog;
import com.ajb.system.config.RequestState;
import com.ajb.system.config.ResultMessage;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@CrossOrigin
@RequestMapping("/web/home")
public class HomeController {
	@Autowired
	private HomeService homeService;
	
	@MyLog(value="首页列表")
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		params.put("isShow", 1);
		
		params.put("type", "首页轮播图");
		List<HomeDO> lbtList = homeService.list(params);
		int lbtTotal = homeService.count(params);
		
		params.put("type", "首页集团简介管理");
		List<HomeDO> jtjjList = homeService.list(params);
		int jtjjTotal = homeService.count(params);
		
		params.put("type", "首页下属企业");
		List<HomeDO> xsqyList = homeService.list(params);
		int xsqyTotal = homeService.count(params);
		
		params.put("type", "首页获奖荣誉");
		List<HomeDO> hjryList = homeService.list(params);
		int hjryTotal = homeService.count(params);
		
		params.put("type", "首页发展历程");
		params.put("sort", "order_num");
		params.put("order", "asc");
		List<HomeDO> fzlcList = homeService.list(params);
		int fzlcTotal = homeService.count(params);

		map.put("lbtList", lbtList);
		map.put("lbtTotal", lbtTotal);
		
		map.put("jtjjList", jtjjList);
		map.put("jtjjTotal", jtjjTotal);
		
		map.put("xsqyList", xsqyList);
		map.put("xsqyTotal", xsqyTotal);
		
		map.put("hjryList", hjryList);
		map.put("hjryTotal", hjryTotal);
		
		map.put("fzlcList", fzlcList);
		map.put("fzlcTotal", fzlcTotal);
		
		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}

	@MyLog(value="首页详情")
	@ResponseBody
	@RequestMapping("/getDetail")
	public ResultMessage getDetail(@RequestParam Map<String, Object> params){
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String id = params.get("id")+"";
		if(TextUtils.isEmpty(id)){
			message.setMessage("查询失败");
			message.setStatus(RequestState.FAIL);
		} else {
			HomeDO detail = homeService.get(Integer.parseInt(id));
			map.put("detail", detail);
			
			message.setMessage("查询成功");
			message.setStatus(RequestState.SUCCESS);
			message.setResultMap(map);
		}
		
		return message;
	}
}
