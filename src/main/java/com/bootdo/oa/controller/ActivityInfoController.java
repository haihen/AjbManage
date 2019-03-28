package com.bootdo.oa.controller;

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

import com.bootdo.oa.domain.ActivityInfoDO;
import com.bootdo.oa.service.ActivityInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * InnoDB free: 7168 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-03-27 15:00:42
 */
 
@Controller
@RequestMapping("/oa/activityInfo")
public class ActivityInfoController {
	@Autowired
	private ActivityInfoService activityInfoService;
	
	@GetMapping()
	@RequiresPermissions("oa:activityInfo:activityInfo")
	String ActivityInfo(){
	    return "oa/activityInfo/activityInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:activityInfo:activityInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ActivityInfoDO> activityInfoList = activityInfoService.list(query);
		int total = activityInfoService.count(query);
		PageUtils pageUtils = new PageUtils(activityInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:activityInfo:add")
	String add(){
	    return "oa/activityInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:activityInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ActivityInfoDO activityInfo = activityInfoService.get(id);
		model.addAttribute("activityInfo", activityInfo);
	    return "oa/activityInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:activityInfo:add")
	public R save( ActivityInfoDO activityInfo){
		if(activityInfoService.save(activityInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:activityInfo:edit")
	public R update( ActivityInfoDO activityInfo){
		activityInfoService.update(activityInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:activityInfo:remove")
	public R remove( Integer id){
		if(activityInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:activityInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		activityInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
