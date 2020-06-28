package com.ajb.home.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
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
@RequestMapping("/system/home/company")
public class HomeCompanyController {
	@Autowired
	private HomeService homeService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("system:home:company")
	String Home(){
	    return "system/home/company";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:home:company")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HomeDO> homeList = homeService.list(query);
		int total = homeService.count(query);
		PageUtils pageUtils = new PageUtils(homeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:home:addcompany")
	String add(){
	    return "system/home/company/add";
	}

	@GetMapping("/edit")
	@RequiresPermissions("system:home:editcompany")
	String edit(@RequestParam("id") Integer id,Model model){
		HomeDO home = homeService.get(id);
		model.addAttribute("company", home);
	    return "system/home/company/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:home:addcompany")
	public R save( HomeDO home){
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
	@RequiresPermissions("system:home:editcompany")
	public R update(@RequestParam("imageFile") MultipartFile imageFile,
					@RequestParam("videoFile") MultipartFile videoFile,
					HomeDO home){
		
		if(imageFile!=null && !imageFile.isEmpty()){
			String fileName = "",fileUrl = "";
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
			fileUrl =FileUtil.reUrl("homeCompanyJsImg");
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
		
		String videoUrl = "";
		
		if(videoFile!=null && !videoFile.isEmpty()){
			String viFileName = "",viFileUrl = "";
			
			viFileName = videoFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(viFileName)!=2) {
				return R.error(1, "视频类型错误，请上传视频文件！");
			}
			// 最大500M
			if (videoFile.getSize() > (500 * 1024 * 1024)) {
				return R.error(1, "视频大小错误，请上传500M大小以内的视频！");
			}
			viFileName = FileUtil.renameToUUID(viFileName);
			viFileUrl =FileUtil.reUrl("homeCompanyJsVideo");
			try {
				FileUtil.uploadFile(videoFile.getBytes(), bootdoConfig.getUploadPath()+viFileUrl, viFileName);
			} catch (Exception e) {
				return R.error();
			}
			videoUrl = "/files/"+viFileUrl+viFileName;
			home.setVideoUrl(videoUrl);
		}
		
		if(TextUtils.isEmpty(home.getVideoUrl())){
			home.setVideoUrl(null);
		}
		
		homeService.update(home);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:home:removecompany")
	public R remove( Integer id){
		if(homeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	
}
