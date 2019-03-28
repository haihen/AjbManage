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

import com.bootdo.oa.domain.TrainTypeDO;
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
@RequestMapping("/oa/trainType")
public class TrainTypeController {
	@Autowired
	private TrainTypeService trainTypeService;
	
	@GetMapping()
	@RequiresPermissions("oa:trainType:trainType")
	String TrainType(){
	    return "oa/trainType/trainType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:trainType:trainType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TrainTypeDO> trainTypeList = trainTypeService.list(query);
		int total = trainTypeService.count(query);
		PageUtils pageUtils = new PageUtils(trainTypeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:trainType:add")
	String add(){
	    return "oa/trainType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:trainType:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TrainTypeDO trainType = trainTypeService.get(id);
		model.addAttribute("trainType", trainType);
	    return "oa/trainType/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:trainType:add")
	public R save( TrainTypeDO trainType){
		if(trainTypeService.save(trainType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:trainType:edit")
	public R update( TrainTypeDO trainType){
		trainTypeService.update(trainType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:trainType:remove")
	public R remove( Integer id){
		if(trainTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:trainType:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		trainTypeService.batchRemove(ids);
		return R.ok();
	}
	
}
