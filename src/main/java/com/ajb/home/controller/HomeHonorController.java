package com.ajb.home.controller;

import java.io.File;
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

import com.ajb.home.domain.HomeDO;
import com.ajb.home.service.HomeService;
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
@RequestMapping("/system/home/honor")
public class HomeHonorController {
	@Autowired
	private HomeService homeService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@RequestMapping()
	@RequiresPermissions("system:home:honor")
	String Home(){
	    return "system/home/honor/honor";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("system:home:honor")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("type", "首页获奖荣誉");
        Query query = new Query(params);
		List<HomeDO> homeList = homeService.list(query);
		int total = homeService.count(query);
		PageUtils pageUtils = new PageUtils(homeList, total);
		return pageUtils;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("system:home:addhonor")
	String add(){
	    return "system/home/honor/add";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("system:home:edithonor")
	String edit(@PathVariable("id") Integer id,Model model){
		HomeDO home = homeService.get(id);
		model.addAttribute("honor", home);
	    return "system/home/honor/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("system:home:addhonor")
	public R save(@RequestParam("imageFile") MultipartFile imageFile,HomeDO home){
		
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
			fileUrl =FileUtil.reUrl("homeHonorImg");
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
			home.setImageUrl("/files/"+fileUrl+fileName);
		}
		home.setType("首页获奖荣誉");
		if(homeService.save(home)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:home:edithonor")
	public R update(@RequestParam("imageFile") MultipartFile imageFile,HomeDO home){
		
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
			fileUrl =FileUtil.reUrl("homeHonorImg");
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
			home.setImageUrl("/files/"+fileUrl+fileName);
		}
		homeService.update(home);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:home:removehonor")
	public R remove( Integer id){
		if(homeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
}
