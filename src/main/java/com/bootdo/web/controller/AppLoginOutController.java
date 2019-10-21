package com.bootdo.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.common.utils.R;
import com.bootdo.web.domain.WebUserDO;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class AppLoginOutController {

	/**
	 * 网站登出
	 */
	@RequestMapping("/loginOutApp")
	public R loginOutWeb( WebUserDO user,HttpServletRequest request,ModelMap map){
		System.out.println("dengchu-----"+request.getSession().getId());
		request.getSession().removeAttribute("webUser");
		return R.ok();
	}
}
