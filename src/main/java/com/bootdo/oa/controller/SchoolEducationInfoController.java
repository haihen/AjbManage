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
import com.bootdo.oa.domain.SchoolEducationInfoDO;
import com.bootdo.oa.service.SchoolEducationInfoService;
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
@RequestMapping("/oa/schoolEducationInfo")
public class SchoolEducationInfoController {
	@Autowired
	private SchoolEducationInfoService schoolEducationInfoService;
	@Autowired
	private SchoolEducationService schoolEducationService;
	
	@GetMapping()
	@RequiresPermissions("oa:schoolEducationInfo:schoolEducationInfo")
	String SchoolEducationInfo(Model model){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 1);
		List<SchoolEducationDO> schoolEducationList = schoolEducationService.list(params);
		model.addAttribute("schoolEducationList", schoolEducationList);
	    return "oa/schoolEducationInfo/schoolEducationInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:schoolEducationInfo:schoolEducationInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolEducationInfoDO> schoolEducationInfoList = schoolEducationInfoService.list(query);
		int total = schoolEducationInfoService.count(query);
		PageUtils pageUtils = new PageUtils(schoolEducationInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:schoolEducationInfo:add")
	String add(Model model){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 1);
		List<SchoolEducationDO> schoolEducationList = schoolEducationService.list(params);
		model.addAttribute("schoolEducationList", schoolEducationList);
	    return "oa/schoolEducationInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:schoolEducationInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		SchoolEducationInfoDO schoolEducationInfo = schoolEducationInfoService.get(id);
		model.addAttribute("schoolEducationInfo", schoolEducationInfo);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level", 1);
		List<SchoolEducationDO> schoolEducationList = schoolEducationService.list(params);
		for(SchoolEducationDO sd : schoolEducationList){
			if(sd.getId()!=null && sd.getId().equals(schoolEducationInfo.getFkTypeId1())){
				sd.setSfxz("abc");
			}
		}
		model.addAttribute("schoolEducationList", schoolEducationList);
	    return "oa/schoolEducationInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:schoolEducationInfo:add")
	public R save( SchoolEducationInfoDO schoolEducationInfo){
		if(schoolEducationInfoService.save(schoolEducationInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:schoolEducationInfo:edit")
	public R update( SchoolEducationInfoDO schoolEducationInfo){
		schoolEducationInfoService.update(schoolEducationInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:schoolEducationInfo:remove")
	public R remove( Integer id){
		if(schoolEducationInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:schoolEducationInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		schoolEducationInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
