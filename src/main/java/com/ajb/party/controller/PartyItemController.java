package com.ajb.party.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.common.utils.PageUtils;
import com.ajb.common.utils.Query;
import com.ajb.common.utils.R;
import com.ajb.party.domain.PartyItemDO;
import com.ajb.party.service.PartyItemService;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@RequestMapping("/system/party/item")
public class PartyItemController {
	@Autowired
	private PartyItemService partyItemService;
	@Autowired
	private AjbTypeService ajbTypeService;
	
	@GetMapping()
	@RequiresPermissions("system:party:item")
	String PartyItem(){
	    return "system/party/partyItem";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:party:item")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PartyItemDO> partyItemList = partyItemService.list(query);
		int total = partyItemService.count(query);
		PageUtils pageUtils = new PageUtils(partyItemList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/getList")
	public List<PartyItemDO> getList(@RequestParam Map<String, Object> params){
		List<PartyItemDO> partyItemList = partyItemService.list(params);
		return partyItemList;
	}
	
	@ResponseBody
	@GetMapping("/getListItem")
	public List<PartyItemDO> getListItem(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<PartyItemDO> partyItemList = partyItemService.list(params);
		return partyItemList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:party:additem")
	String add(Model model){
		
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("type", "党的建设");
//		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
//		String[] menuList = new String[ajbTypeList.size()];
//		for(int i=0;i<ajbTypeList.size();i++){
//			menuList[i] = ajbTypeList.get(i).getName();
//		}
		String[] menuList = {"主题教育"};
		model.addAttribute("menuList",menuList);
	    return "system/party/addItem";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:party:edititem")
	String edit(@PathVariable("id") Integer id,Model model){
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("type", "党的建设");
//		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
//		String[] menuList = new String[ajbTypeList.size()];
//		for(int i=0;i<ajbTypeList.size();i++){
//			menuList[i] = ajbTypeList.get(i).getName();
//		}
		String[] menuList = {"主题教育"};
		model.addAttribute("menuList",menuList);
		
		PartyItemDO partyItem = partyItemService.get(id);
		
		model.addAttribute("partyItem", partyItem);
		model.addAttribute("menuList",menuList);
	    return "system/party/editItem";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:party:additem")
	public R save( PartyItemDO partyItem){
		if(partyItemService.save(partyItem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:party:edititem")
	public R update(PartyItemDO partyItem){
		partyItemService.update(partyItem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:party:removeitem")
	public R remove( Integer id){
		if(partyItemService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	
}
