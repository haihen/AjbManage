package com.bootdo.web.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.web.domain.ActivityDO;
import com.bootdo.web.domain.WheelDO;
import com.bootdo.web.service.ActivityService;
import com.bootdo.web.service.WheelService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * InnoDB free: 9216 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-05 09:17:50
 */
 
@Controller
@RequestMapping("/web/activity")
public class ActivityController {
	@Autowired
	private ActivityService activityService;
	
	@GetMapping()
	@RequiresPermissions("web:activity:activity")
	String Wheel(){
	    return "web/activity/activity";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("web:activity:activity")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ActivityDO> activityList = activityService.list(query);
		int total = activityService.count(query);
		PageUtils pageUtils = new PageUtils(activityList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("web:activity:add")
	String add(){
	    return "web/activity/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:activity:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ActivityDO activity = activityService.get(id);
		model.addAttribute("activity", activity);
	    return "web/activity/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:activity:add")
	public R save(ActivityDO activity){
		
		if(activityService.save(activity)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:activity:edit")
	public R update(ActivityDO activity){
		
		activityService.update(activity);
		return R.ok();
	}
	
	/**
	 * 发布
	 */
	@ResponseBody
	@RequestMapping("/display")
	@RequiresPermissions("web:activity:display")
	public R display(ActivityDO activity){
		
		activityService.display(activity);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("web:activity:remove")
	public R remove( Integer id){
		if(activityService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
}
