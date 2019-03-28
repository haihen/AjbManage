package com.bootdo.oa.controller;

import java.util.HashMap;
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

import com.bootdo.oa.domain.SchoolEducationDO;
import com.bootdo.oa.domain.TrainInfoDO;
import com.bootdo.oa.domain.TrainTypeDO;
import com.bootdo.oa.service.TrainInfoService;
import com.bootdo.oa.service.TrainTypeService;
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
@RequestMapping("/oa/trainInfo")
public class TrainInfoController {
	@Autowired
	private TrainInfoService trainInfoService;
	@Autowired
	private TrainTypeService trainTypeService;
	
	@GetMapping()
	@RequiresPermissions("oa:trainInfo:trainInfo")
	String TrainInfo(){
	    return "oa/trainInfo/trainInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:trainInfo:trainInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TrainInfoDO> trainInfoList = trainInfoService.list(query);
		int total = trainInfoService.count(query);
		PageUtils pageUtils = new PageUtils(trainInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:trainInfo:add")
	String add(Model model){
		Map<String, Object> params = new HashMap<String, Object>();
		List<TrainTypeDO> trainTypeList = trainTypeService.list(params);
		model.addAttribute("trainTypeList", trainTypeList);
	    return "oa/trainInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:trainInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TrainInfoDO trainInfo = trainInfoService.get(id);
		model.addAttribute("trainInfo", trainInfo);
	    return "oa/trainInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:trainInfo:add")
	public R save( TrainInfoDO trainInfo){
		if(trainInfoService.save(trainInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:trainInfo:edit")
	public R update( TrainInfoDO trainInfo){
		trainInfoService.update(trainInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:trainInfo:remove")
	public R remove( Integer id){
		if(trainInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:trainInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		trainInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
