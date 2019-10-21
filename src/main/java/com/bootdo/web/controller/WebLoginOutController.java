package com.bootdo.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootdo.oa.domain.ActivityInfoDO;
import com.bootdo.oa.domain.NoticeInfoDO;
import com.bootdo.oa.service.ActivityInfoService;
import com.bootdo.oa.service.NoticeInfoService;
import com.bootdo.web.domain.WebUserDO;
import com.bootdo.web.domain.WheelDO;
import com.bootdo.web.service.WheelService;

@Controller
@RequestMapping("/home")
public class WebLoginOutController {

	@Autowired
	private ActivityInfoService activityInfoService;
	@Autowired
	private NoticeInfoService noticeInfoService;
	@Autowired
	private WheelService wheelService;
	
	/**
	 * 网站登出
	 */
	@GetMapping("/loginOutWeb")
	public String loginOutWeb( WebUserDO user,HttpServletRequest request,ModelMap map,Model model){
		System.out.println("dengchu-----"+request.getSession().getId());
		
		//通知公告
		Map<String, Object> tzggMap = new HashMap<String, Object>();
		//特色活动
		Map<String, Object> tshdMap = new HashMap<String, Object>();
		//轮播图
		Map<String, Object> lbtMap = new HashMap<String, Object>();
		
		tshdMap.put("limit", 5);
		tshdMap.put("offset",0);
		List<ActivityInfoDO> tshdList = activityInfoService.list(tshdMap);
		
		tzggMap.put("limit", 5);
		tzggMap.put("offset",0);
		List<NoticeInfoDO> tzggList = noticeInfoService.list(tzggMap);
		
		List<WheelDO> lbtList = wheelService.list(lbtMap);
		
		model.addAttribute("tshdList", tshdList);
		model.addAttribute("tzggList", tzggList);
		model.addAttribute("lbtList", lbtList);
		
		request.getSession().removeAttribute("webUser");
		return "home";
	}
}
