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
import com.bootdo.oa.service.SchoolEducationService;
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
@RequestMapping("/oa/schoolEducation")
public class SchoolEducationController {
	@Autowired
	private SchoolEducationService schoolEducationService;
	
	@GetMapping("/1")
	@RequiresPermissions("oa:schoolEducation:schoolEducation")
	String SchoolEducation1(){
	    return "oa/schoolEducation/schoolEducation1";
	}
	@GetMapping("/2")
	@RequiresPermissions("oa:schoolEducation:schoolEducation")
	String SchoolEducation2(){
	    return "oa/schoolEducation/schoolEducation2";
	}
	
	@ResponseBody
	@GetMapping("/1/list")
	@RequiresPermissions("oa:schoolEducation:schoolEducation")
	public PageUtils list1(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolEducationDO> schoolEducationList = schoolEducationService.list(query);
		int total = schoolEducationService.count(query);
		PageUtils pageUtils = new PageUtils(schoolEducationList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/2/list")
	@RequiresPermissions("oa:schoolEducation:schoolEducation")
	public PageUtils list2(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolEducationDO> schoolEducationList = schoolEducationService.list2(query);
		int total = schoolEducationService.count(query);
		PageUtils pageUtils = new PageUtils(schoolEducationList, total);
		return pageUtils;
	}
	
	@GetMapping("/1/add1")
	@RequiresPermissions("oa:schoolEducation:add")
	String add1(){
	    return "oa/schoolEducation/add1";
	}
	
	@GetMapping("/2/add2")
	@RequiresPermissions("oa:schoolEducation:add")
	String add2(){
	    return "oa/schoolEducation/add2";
	}

	@GetMapping("/getType1")
	@ResponseBody
	public List<SchoolEducationDO> getType1(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 1);
		List<SchoolEducationDO> schoolEducationList = schoolEducationService.list(params);
		return schoolEducationList;
	}
	
	@GetMapping("/1/edit1/{id}")
	@RequiresPermissions("oa:schoolEducation:edit")
	String edit1(@PathVariable("id") Integer id,Model model){
		SchoolEducationDO schoolEducation = schoolEducationService.get(id);
		model.addAttribute("schoolEducation", schoolEducation);
	    return "oa/schoolEducation/edit1";
	}
	
	@GetMapping("/2/edit2/{id}")
	@RequiresPermissions("oa:schoolEducation:edit")
	String edit2(@PathVariable("id") Integer id,Model model){
		SchoolEducationDO schoolEducation = schoolEducationService.get(id);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 1);
		List<SchoolEducationDO> schoolEducationList = schoolEducationService.list(params);
		for(SchoolEducationDO sd : schoolEducationList){
			if(sd.getId()!=null && sd.getId().equals(schoolEducation.getPid())){
				sd.setSfxz("abc");
				System.out.println("------------------dsfasdf");
			}
		}
		model.addAttribute("schoolEducationList", schoolEducationList);
		model.addAttribute("schoolEducation", schoolEducation);
	    return "oa/schoolEducation/edit2";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:schoolEducation:add")
	public R save( SchoolEducationDO schoolEducation){
		if(schoolEducationService.save(schoolEducation)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:schoolEducation:edit")
	public R update( SchoolEducationDO schoolEducation){
		schoolEducationService.update(schoolEducation);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:schoolEducation:remove")
	public R remove( Integer id){
		if(schoolEducationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:schoolEducation:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		schoolEducationService.batchRemove(ids);
		return R.ok();
	}
	
}
