package com.bootdo.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bootdo.web.domain.WebUserDO;
import com.bootdo.web.service.WebUserService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * InnoDB free: 9216 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-06 16:12:07
 */
 
@Controller
@RequestMapping("/web/user")
@SessionAttributes("webUser")
public class WebUserController {
	@Autowired
	private WebUserService userService;
	
	@GetMapping()
	@RequiresPermissions("web:user:user")
	String User(){
	    return "web/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("web:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WebUserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("web:user:add")
	String add(){
	    return "web/user/add";
	}

	@GetMapping("/edit/{userId}")
	@RequiresPermissions("web:user:edit")
	String edit(@PathVariable("userId") Integer userId,Model model){
		WebUserDO user = userService.get(userId);
		model.addAttribute("user", user);
	    return "web/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:user:add")
	public R save( WebUserDO user){
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 网站保存
	 */
	@ResponseBody
	@PostMapping("/saveWeb")
	@RequiresPermissions("web:user:add")
	public R saveWeb( WebUserDO user){
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:user:edit")
	public R update( WebUserDO user){
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("web:user:remove")
	public R remove( Integer userId){
		if(userService.remove(userId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:user:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] userIds){
		userService.batchRemove(userIds);
		return R.ok();
	}
	
}
