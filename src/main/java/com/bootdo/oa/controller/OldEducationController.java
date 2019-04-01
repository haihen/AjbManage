package com.bootdo.oa.controller;

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

import com.bootdo.oa.domain.OldEducationDO;
import com.bootdo.oa.domain.TrainInfoDO;
import com.bootdo.oa.service.OldEducationService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.CheckFileFormatUtil;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
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
@RequestMapping("/oa/oldEducation")
public class OldEducationController {
	@Autowired
	private OldEducationService oldEducationService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("oa:oldEducation:oldEducation")
	String OldEducation(){
	    return "oa/oldEducation/oldEducation";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:oldEducation:oldEducation")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OldEducationDO> oldEducationList = oldEducationService.list(query);
		int total = oldEducationService.count(query);
		PageUtils pageUtils = new PageUtils(oldEducationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:oldEducation:add")
	String add(){
	    return "oa/oldEducation/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:oldEducation:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OldEducationDO oldEducation = oldEducationService.get(id);
		model.addAttribute("oldEducation", oldEducation);
	    return "oa/oldEducation/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:oldEducation:add")
	public R save(@RequestParam("coverImgFile") MultipartFile coverImgFile,OldEducationDO oldEducation){
		String fileName = "",fileUrl = "";
		//缩略图
		if(coverImgFile!=null && !coverImgFile.isEmpty()){
			fileName = coverImgFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=0) {
				return R.error(1001, "图片类型错误，请上传图片文件！");
			}
			// 最大4m
			if (coverImgFile.getSize() > (4 * 1024 * 1024)) {
				return R.error(1002, "图片大小错误，请上传4M大小以内的图片！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("oldEduImg");
			try {
				FileUtil.uploadFile(coverImgFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
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
			oldEducation.setCoverImg("/files/"+fileUrl+fileName);
		}

		if(oldEducationService.save(oldEducation)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:oldEducation:edit")
	public R update(@RequestParam("coverImgFile") MultipartFile coverImgFile,OldEducationDO oldEducation){
		String fileName = "",fileUrl = "";
		//缩略图
		if(coverImgFile!=null && !coverImgFile.isEmpty()){
			fileName = coverImgFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=0) {
				return R.error(1001, "图片类型错误，请上传图片文件！");
			}
			// 最大4m
			if (coverImgFile.getSize() > (4 * 1024 * 1024)) {
				return R.error(1002, "图片大小错误，请上传4M大小以内的图片！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("oldEduImg");
			try {
				FileUtil.uploadFile(coverImgFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
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
			oldEducation.setCoverImg("/files/"+fileUrl+fileName);
		}

		oldEducationService.update(oldEducation);
		return R.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:oldEducation:remove")
	public R remove( Integer id){
		if(oldEducationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:oldEducation:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		oldEducationService.batchRemove(ids);
		return R.ok();
	}
	
}
