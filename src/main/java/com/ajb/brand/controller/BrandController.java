package com.ajb.brand.controller;

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

import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.brand.domain.BrandDO;
import com.ajb.brand.service.BrandService;
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
@RequestMapping("/system/brand")
public class BrandController {
	@Autowired
	private BrandService brandService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	
	@RequestMapping()
	@RequiresPermissions("system:brand:brand")
	String Brand(){
	    return "system/brand/brand";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("system:brand:brand")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BrandDO> brandList = brandService.list(query);
		int total = brandService.count(query);
		PageUtils pageUtils = new PageUtils(brandList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String type = params.get("type")+"";
		
		List<BrandDO> brandList = brandService.list(params);
		map.put("brandList", brandList);
		
		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("system:brand:add")
	String add(Model model){
//		String[] menuList = {"品牌故事","安居要闻","社会责任","文案策划","员工活动"};
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", "品牌文化");
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
		String[] menuList = new String[ajbTypeList.size()];
		for(int i=0;i<ajbTypeList.size();i++){
			menuList[i] = ajbTypeList.get(i).getName();
		}
		model.addAttribute("menuList",menuList);
	    return "system/brand/add";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("system:brand:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		BrandDO brand = brandService.get(id);
		model.addAttribute("brand", brand);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", "品牌文化");
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
		String[] menuList = new String[ajbTypeList.size()];
		for(int i=0;i<ajbTypeList.size();i++){
			menuList[i] = ajbTypeList.get(i).getName();
		}
		model.addAttribute("menuList",menuList);
	    return "system/brand/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("system:brand:add")
	public R save(@RequestParam("imageFile") MultipartFile imageFile,BrandDO brand){
		
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
			fileUrl =FileUtil.reUrl("brandImg");
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
			brand.setImageUrl("/files/"+fileUrl+fileName);
		}
		if(brandService.save(brand)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:brand:edit")
	public R update(@RequestParam("imageFile") MultipartFile imageFile,BrandDO brand){
		
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
			fileUrl =FileUtil.reUrl("brandImg");
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
			brand.setImageUrl("/files/"+fileUrl+fileName);
		}
		brandService.update(brand);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:brand:remove")
	public R remove( Integer id){
		if(brandService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
}
