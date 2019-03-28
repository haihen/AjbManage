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

import com.bootdo.oa.domain.NoticeTypeDO;
import com.bootdo.oa.service.NoticeTypeService;
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
@RequestMapping("/oa/noticeType")
public class NoticeTypeController {
	@Autowired
	private NoticeTypeService noticeTypeService;
	
	@GetMapping()
	@RequiresPermissions("oa:noticeType:noticeType")
	String NoticeType(){
	    return "oa/noticeType/noticeType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:noticeType:noticeType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NoticeTypeDO> noticeTypeList = noticeTypeService.list(query);
		int total = noticeTypeService.count(query);
		PageUtils pageUtils = new PageUtils(noticeTypeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:noticeType:add")
	String add(){
	    return "oa/noticeType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:noticeType:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		NoticeTypeDO noticeType = noticeTypeService.get(id);
		model.addAttribute("noticeType", noticeType);
	    return "oa/noticeType/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:noticeType:add")
	public R save( NoticeTypeDO noticeType){
		if(noticeTypeService.save(noticeType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:noticeType:edit")
	public R update( NoticeTypeDO noticeType){
		noticeTypeService.update(noticeType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:noticeType:remove")
	public R remove( Integer id){
		if(noticeTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:noticeType:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		noticeTypeService.batchRemove(ids);
		return R.ok();
	}
	
}
