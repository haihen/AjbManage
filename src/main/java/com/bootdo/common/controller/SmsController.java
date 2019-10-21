package com.bootdo.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.R;
import com.bootdo.common.utils.SmsMessage;
import com.bootdo.common.utils.SmsUtils;
import com.bootdo.web.dao.WebUserDao;

@Controller
@RequestMapping("/home")
@CrossOrigin
public class SmsController {
	
	@Autowired
	private WebUserDao userDao;

	@ResponseBody
	@PostMapping("/sendSms")
	public R sendSms(HttpServletRequest request,String mobile){
		SmsMessage smsMessage = new SmsMessage();
		
		if(TextUtils.isEmpty(mobile)){
			return R.error(2,"请输入手机号");
		}
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", mobile);
		count = userDao.count(map);
		if(count>0){
			return R.error(2,"此手机号已注册！");
		}
		
		smsMessage = SmsUtils.sendSmsMessageCode(request,mobile);
		
		if("OK".equals(smsMessage.getCode())){
			return R.ok().put("smsCode", smsMessage.getSmsCode());
		} else {
			return R.error(2,"发送短信失败！");
		}

	}
	
	@ResponseBody
	@PostMapping("/sendSmsApp")
	public R sendSmsApp(HttpServletRequest request,String mobile){
		SmsMessage smsMessage = new SmsMessage();
		
		if(TextUtils.isEmpty(mobile)){
			return R.error(2,"请输入手机号");
		}
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", mobile);
		count = userDao.count(map);
		if(count>0){
			return R.error(2,"此手机号已注册！");
		}
		
		smsMessage = SmsUtils.sendSmsMessageCodeApp(request,mobile);
		
		if("OK".equals(smsMessage.getCode())){
			return R.ok().put("smsCode", smsMessage.getSmsCode());
		} else {
			return R.error(2,"发送短信失败！");
		}

	}
}
