package com.ajb.science.controller;

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

import com.ajb.science.domain.ScienceDO;
import com.ajb.science.service.ScienceService;
import com.ajb.system.config.RequestState;
import com.ajb.system.config.ResultMessage;
import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.common.config.BootdoConfig;
import com.ajb.common.utils.CheckFileFormatUtil;
import com.ajb.common.utils.FileType;
import com.ajb.common.utils.FileUtil;
import com.ajb.common.utils.PageUtils;
import com.ajb.common.utils.Query;
import com.ajb.common.utils.R;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@RequestMapping("/system/science")
public class ScienceController {
	@Autowired
	private ScienceService scienceService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	
	@RequestMapping()
	@RequiresPermissions("system:science:science")
	String Science(){
	    return "system/science/science";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("system:science:science")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScienceDO> scienceList = scienceService.list(query);
		int total = scienceService.count(query);
		PageUtils pageUtils = new PageUtils(scienceList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<ScienceDO> scienceList = scienceService.list(params);
		map.put("scienceList", scienceList);
		
		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("system:science:add")
	String add(Model model){
//		String[] menuList = {"工程技术","建筑产业化","节能技术"};
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", "科技创新");
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
		String[] menuList = new String[ajbTypeList.size()];
		for(int i=0;i<ajbTypeList.size();i++){
			menuList[i] = ajbTypeList.get(i).getName();
		}
		model.addAttribute("menuList",menuList);
	    return "system/science/add";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("system:science:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ScienceDO science = scienceService.get(id);
		model.addAttribute("science", science);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", "科技创新");
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
		String[] menuList = new String[ajbTypeList.size()];
		for(int i=0;i<ajbTypeList.size();i++){
			menuList[i] = ajbTypeList.get(i).getName();
		}
		model.addAttribute("menuList",menuList);
	    return "system/science/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("system:science:add")
	public R save(@RequestParam("imageFile") MultipartFile imageFile,ScienceDO science){
		
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
			fileUrl =FileUtil.reUrl("scienceImg");
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
			science.setImageUrl("/files/"+fileUrl+fileName);
		}
		if(scienceService.save(science)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:science:edit")
	public R update(@RequestParam("imageFile") MultipartFile imageFile,ScienceDO science){
		
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
			fileUrl =FileUtil.reUrl("scienceImg");
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
			science.setImageUrl("/files/"+fileUrl+fileName);
		}
		scienceService.update(science);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:science:remove")
	public R remove( Integer id){
		if(scienceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
}
