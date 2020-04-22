package com.ajb.home.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajb.home.domain.HomeDO;
import com.ajb.home.service.HomeService;
import com.ajb.common.utils.PageUtils;
import com.ajb.common.utils.Query;
import com.ajb.common.utils.R;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@RequestMapping("/system/home/develop")
public class HomeDevelopController {
	@Autowired
	private HomeService homeService;
	
	@RequestMapping()
	@RequiresPermissions("system:home:develop")
	String Home(){
	    return "system/home/develop/develop";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("system:home:develop")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("type", "首页发展历程");
		params.put("sort", "order_num");
		params.put("order", "asc");
		//查询列表数据
        Query query = new Query(params);
		List<HomeDO> homeList = homeService.list(query);
		int total = homeService.count(query);
		PageUtils pageUtils = new PageUtils(homeList, total);
		return pageUtils;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("system:home:adddevelop")
	String add(){
	    return "system/home/develop/add";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("system:home:editdevelop")
	String edit(@PathVariable("id") Integer id,Model model){
		HomeDO home = homeService.get(id);
		model.addAttribute("develop", home);
	    return "system/home/develop/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("system:home:adddevelop")
	public R save( HomeDO home){
		home.setType("首页发展历程");
		if(homeService.save(home)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:home:editdevelop")
	public R update( HomeDO home){
		homeService.update(home);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:home:removedevelop")
	public R remove( Integer id){
		if(homeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	
}
