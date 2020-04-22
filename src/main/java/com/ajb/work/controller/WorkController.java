package com.ajb.work.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ajb.work.domain.WorkDO;
import com.ajb.work.service.WorkService;
import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.common.config.BootdoConfig;
import com.ajb.common.utils.CheckFileFormatUtil;
import com.ajb.common.utils.FileType;
import com.ajb.common.utils.FileUtil;
import com.ajb.common.utils.PageUtils;
import com.ajb.common.utils.Query;
import com.ajb.common.utils.R;
import com.ajb.system.config.RequestState;
import com.ajb.system.config.ResultMessage;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@RequestMapping("/system/work")
public class WorkController {
	@Autowired
	private WorkService workService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	
	@RequestMapping()
	@RequiresPermissions("system:work:work")
	String Work(){
	    return "system/work/work";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("system:work:work")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WorkDO> workList = workService.list(query);
		int total = workService.count(query);
		PageUtils pageUtils = new PageUtils(workList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String type = params.get("type")+"";
		
		if("房地产开发".equals(type)||
				"市政基础设施".equals(type)||
				"供热民生".equals(type)){
			Query query = new Query(params);
			List<WorkDO> workList = workService.list(query);
			int total = workService.count(query);
			map.put("workList", workList);
			map.put("total", total);
		} else if("物业服务".equals(type)){
			params.put("type", "企业简介");
			List<WorkDO> qyjjList = workService.list(params);
			map.put("qyjjList", qyjjList);
			
			params.put("type", "企业特色");
			List<WorkDO> qytsList = workService.list(params);
			map.put("qytsList", qytsList);
			
			params.put("type", "体系认证");
			List<WorkDO> txrzList = workService.list(params);
			map.put("txrzList", txrzList);
			
			params.put("type", "体系认证图片");
			List<WorkDO> txrztpList = workService.list(params);
			map.put("txrztpList", txrztpList);
			
			params.put("type", "获奖荣誉");
			List<WorkDO> hjryList = workService.list(params);
			map.put("hjryList", hjryList);
		} else {
			List<WorkDO> workList = workService.list(params);
			map.put("workList", workList);
		}

		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("system:work:add")
	String add(Model model){
		String[] menuList = {"房地产开发","市政基础设施","商业运营","建筑节能及新技术推广","企业简介","企业特色","体系认证","体系认证图片","获奖荣誉","供热民生"};
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("type", "业务领域");
//		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
//		String[] menuList = new String[ajbTypeList.size()];
//		for(int i=0;i<ajbTypeList.size();i++){
//			menuList[i] = ajbTypeList.get(i).getName();
//		}
		model.addAttribute("menuList",menuList);
	    return "system/work/add";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("system:work:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		WorkDO work = workService.get(id);
		model.addAttribute("work", work);
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("type", "业务领域");
//		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
//		String[] menuList = new String[ajbTypeList.size()];
//		for(int i=0;i<ajbTypeList.size();i++){
//			menuList[i] = ajbTypeList.get(i).getName();
//		}
		String[] menuList = {"房地产开发","市政基础设施","商业运营","建筑节能及新技术推广","企业简介","企业特色","体系认证","体系认证图片","获奖荣誉","供热民生"};
		model.addAttribute("menuList",menuList);
	    return "system/work/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("system:work:add")
	public R save(@RequestParam("imageFile") MultipartFile imageFile,WorkDO work){
		
		String fileName = "",fileUrl = "";
		
		if(imageFile!=null && !imageFile.isEmpty()){
			fileName = imageFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=0) {
				return R.error(1001, "图片类型错误，请上传图片文件！");
			}
			// 最大4m
			if (imageFile.getSize() > (4 * 1024 * 1024)) {
				return R.error(1002, "图片大小错误，请上传4M大小以内的图片！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("workImg");
			try {
				FileUtil.uploadFile(imageFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
				String fName = bootdoConfig.getUploadPath()+fileUrl+System.getProperty("file.separator")+fileName;
				boolean isImage = false;
				if(CheckFileFormatUtil.getFileType(fName)!=null){
					isImage = true;
				}
				if(!isImage){
					File imgFile = new File(fName);
					imgFile.delete();
					return R.error(1001, "图片类型错误，请上传图片文件！");
				}
			} catch (Exception e) {
				return R.error();
			}
			work.setImageUrl("/files/"+fileUrl+fileName);
		}
		if(workService.save(work)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:work:edit")
	public R update(@RequestParam("imageFile") MultipartFile imageFile,WorkDO work){
		
		String fileName = "",fileUrl = "";
		
		if(imageFile!=null && !imageFile.isEmpty()){
			fileName = imageFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=0) {
				return R.error(1001, "图片类型错误，请上传图片文件！");
			}
			// 最大4m
			if (imageFile.getSize() > (4 * 1024 * 1024)) {
				return R.error(1002, "图片大小错误，请上传4M大小以内的图片！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("workImg");
			try {
				FileUtil.uploadFile(imageFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
				String fName = bootdoConfig.getUploadPath()+fileUrl+System.getProperty("file.separator")+fileName;
				boolean isImage = false;
				if(CheckFileFormatUtil.getFileType(fName)!=null){
					isImage = true;
				}
				if(!isImage){
					File imgFile = new File(fName);
					imgFile.delete();
					return R.error(1001, "图片类型错误，请上传图片文件！");
				}
			} catch (Exception e) {
				return R.error();
			}
			work.setImageUrl("/files/"+fileUrl+fileName);
		}
		workService.update(work);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:work:remove")
	public R remove( Integer id){
		if(workService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
}
