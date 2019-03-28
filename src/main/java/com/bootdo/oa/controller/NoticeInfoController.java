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

import com.bootdo.oa.domain.NoticeInfoDO;
import com.bootdo.oa.service.NoticeInfoService;
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
@RequestMapping("/oa/noticeInfo")
public class NoticeInfoController {
	@Autowired
	private NoticeInfoService noticeInfoService;
	
	@GetMapping()
	@RequiresPermissions("oa:noticeInfo:noticeInfo")
	String NoticeInfo(){
	    return "oa/noticeInfo/noticeInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:noticeInfo:noticeInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NoticeInfoDO> noticeInfoList = noticeInfoService.list(query);
		int total = noticeInfoService.count(query);
		PageUtils pageUtils = new PageUtils(noticeInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:noticeInfo:add")
	String add(){
	    return "oa/noticeInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:noticeInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		NoticeInfoDO noticeInfo = noticeInfoService.get(id);
		model.addAttribute("noticeInfo", noticeInfo);
	    return "oa/noticeInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:noticeInfo:add")
	public R save( NoticeInfoDO noticeInfo){
		if(noticeInfoService.save(noticeInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:noticeInfo:edit")
	public R update( NoticeInfoDO noticeInfo){
		noticeInfoService.update(noticeInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:noticeInfo:remove")
	public R remove( Integer id){
		if(noticeInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:noticeInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		noticeInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
