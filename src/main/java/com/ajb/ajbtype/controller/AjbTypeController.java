package com.ajb.ajbtype.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.common.utils.PageUtils;
import com.ajb.common.utils.Query;
import com.ajb.common.utils.R;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@RequestMapping("/system/ajb/type")
public class AjbTypeController {
	@Autowired
	private AjbTypeService ajbTypeService;
	
	@GetMapping()
	@RequiresPermissions("system:ajb:type")
	String AjbType(){
	    return "system/ajb/type";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:ajb:type")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(query);
		int total = ajbTypeService.count(query);
		PageUtils pageUtils = new PageUtils(ajbTypeList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/getList")
	public List<AjbTypeDO> getList(@RequestParam Map<String, Object> params){
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(params);
		return ajbTypeList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:ajb:addtype")
	String add(Model model){
		String[] menuList = {"新闻中心","党的建设","业务领域","科技创新","品牌文化","人力资源","商业合作"};
		model.addAttribute("menuList",menuList);
	    return "system/ajb/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:ajb:edittype")
	String edit(@PathVariable("id") Integer id,Model model){
		String[] menuList = {"新闻中心","党的建设","业务领域","科技创新","品牌文化","人力资源","商业合作"};
		
		AjbTypeDO ajbType = ajbTypeService.get(id);
		
		model.addAttribute("ajbType", ajbType);
		model.addAttribute("menuList",menuList);
	    return "system/ajb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:ajb:addtype")
	public R save( AjbTypeDO ajbType){
		if(ajbTypeService.save(ajbType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:ajb:edittype")
	public R update(AjbTypeDO ajbType){
		ajbTypeService.update(ajbType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:ajb:removetype")
	public R remove( Integer id){
		if(ajbTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	
}
