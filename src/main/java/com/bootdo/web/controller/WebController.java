package com.bootdo.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 网站页面
 * 
 * @author yuyang
 * @email 511633975@qq.com
 * @date 2019-03-26 15:02:00
 */
 
@Controller
public class WebController {
	
	@GetMapping("/home")
	public String index(Model model){
		
		return "home";
	}
	
}
