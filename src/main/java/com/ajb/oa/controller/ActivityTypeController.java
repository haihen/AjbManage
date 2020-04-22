package com.ajb.oa.controller;

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

import com.ajb.common.utils.PageUtils;
import com.ajb.common.utils.Query;
import com.ajb.common.utils.R;
import com.ajb.oa.domain.ActivityTypeDO;
import com.ajb.oa.service.ActivityTypeService;

/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
 
@Controller
@RequestMapping("/oa/activityType")
public class ActivityTypeController {
	@Autowired
	private ActivityTypeService activityTypeService;
	
	@GetMapping()
	@RequiresPermissions("oa:activityType:activityType")
	String ActivityType(){
	    return "oa/activityType/activityType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:activityType:activityType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ActivityTypeDO> activityTypeList = activityTypeService.list(query);
		int total = activityTypeService.count(query);
		PageUtils pageUtils = new PageUtils(activityTypeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:activityType:add")
	String add(){
	    return "oa/activityType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:activityType:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ActivityTypeDO activityType = activityTypeService.get(id);
		model.addAttribute("activityType", activityType);
	    return "oa/activityType/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:activityType:add")
	public R save( ActivityTypeDO activityType){
		if(activityTypeService.save(activityType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:activityType:edit")
	public R update( ActivityTypeDO activityType){
		activityTypeService.update(activityType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:activityType:remove")
	public R remove( Integer id){
		if(activityTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:activityType:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		activityTypeService.batchRemove(ids);
		return R.ok();
	}
	
}
