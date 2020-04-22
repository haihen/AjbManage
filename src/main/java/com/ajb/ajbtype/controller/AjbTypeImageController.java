package com.ajb.ajbtype.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ajb.ajbtype.domain.AjbTypeImageDO;
import com.ajb.ajbtype.service.AjbTypeImageService;
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
@RequestMapping("/system/ajb/typeImage")
public class AjbTypeImageController {
	@Autowired
	private AjbTypeImageService ajbTypeImageService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("system:ajb:typeimage")
	String AjbType(){
	    return "system/ajb/typeImage";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:ajb:typeimage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AjbTypeImageDO> ajbTypeList = ajbTypeImageService.list(query);
		int total = ajbTypeImageService.count(query);
		PageUtils pageUtils = new PageUtils(ajbTypeList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/getList")
	public List<AjbTypeImageDO> getList(@RequestParam Map<String, Object> params){
		List<AjbTypeImageDO> ajbTypeList = ajbTypeImageService.list(params);
		return ajbTypeList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:ajb:addtypeimage")
	String add(Model model){
		String[] menuList = {"新闻中心","党的建设","业务领域","科技创新","品牌文化","人力资源","商业合作"};
		model.addAttribute("menuList",menuList);
	    return "system/ajb/addImage";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:ajb:edittypeimage")
	String edit(@PathVariable("id") Integer id,Model model){
		String[] menuList = {"新闻中心","党的建设","业务领域","科技创新","品牌文化","人力资源","商业合作"};
		
		AjbTypeImageDO ajbType = ajbTypeImageService.get(id);
		
		model.addAttribute("ajbType", ajbType);
		model.addAttribute("menuList",menuList);
	    return "system/ajb/editImage";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:ajb:addtype")
	public R save(@RequestParam("imageFile") MultipartFile imageFile,AjbTypeImageDO ajbType){
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
			fileUrl =FileUtil.reUrl("ajbTypeImg");
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
			ajbType.setImageUrl("/files/"+fileUrl+fileName);
		}
		if(ajbTypeImageService.save(ajbType)>0){
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
	public R update(@RequestParam("imageFile") MultipartFile imageFile,AjbTypeImageDO ajbType){
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
			ajbType.setImageUrl("/files/"+fileUrl+fileName);
		}
		ajbTypeImageService.update(ajbType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:ajb:removetype")
	public R remove( Integer id){
		if(ajbTypeImageService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	
}
