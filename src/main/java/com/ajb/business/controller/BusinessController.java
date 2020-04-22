package com.ajb.business.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import com.ajb.business.domain.BusinessDO;
import com.ajb.business.service.BusinessService;
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
@RequestMapping("/system/business")
public class BusinessController {
	@Autowired
	private BusinessService businessService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@RequestMapping()
	@RequiresPermissions("system:business:business")
	String Business(){
	    return "system/business/business";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("system:business:business")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("type", "商业合作");
        Query query = new Query(params);
		List<BusinessDO> businessList = businessService.list(query);
		int total = businessService.count(query);
		PageUtils pageUtils = new PageUtils(businessList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String type = params.get("type")+"";
		List<BusinessDO> businessList = businessService.list(params);
		map.put("businessList", businessList);
		
		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("system:business:add")
	String add(){
	    return "system/business/add";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("system:business:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		BusinessDO business = businessService.get(id);
		model.addAttribute("business", business);
	    return "system/business/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("system:business:add")
	public R save(@RequestParam("imageFile") MultipartFile imageFile,BusinessDO business){
		
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
			fileUrl =FileUtil.reUrl("businessHonorImg");
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
			business.setImageUrl("/files/"+fileUrl+fileName);
		}
		if(businessService.save(business)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:business:edit")
	public R update(@RequestParam("imageFile") MultipartFile imageFile,BusinessDO business){
		
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
			fileUrl =FileUtil.reUrl("businessHonorImg");
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
			business.setImageUrl("/files/"+fileUrl+fileName);
		}
		businessService.update(business);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:business:remove")
	public R remove( Integer id){
		if(businessService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
}
