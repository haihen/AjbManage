package com.bootdo.oa.controller;

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
import org.thymeleaf.util.TextUtils;

import com.bootdo.oa.domain.SchoolEducationDO;
import com.bootdo.oa.domain.TrainInfoDO;
import com.bootdo.oa.domain.TrainTypeDO;
import com.bootdo.oa.service.TrainInfoService;
import com.bootdo.oa.service.TrainTypeService;
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
@RequestMapping("/oa/trainInfo")
public class TrainInfoController {
	@Autowired
	private TrainInfoService trainInfoService;
	@Autowired
	private TrainTypeService trainTypeService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("oa:trainInfo:trainInfo")
	String TrainInfo(Model model){
		Map<String, Object> params = new HashMap<String, Object>();
		List<TrainTypeDO> trainTypeList = trainTypeService.list(params);
		model.addAttribute("trainTypeList", trainTypeList);
	    return "oa/trainInfo/trainInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:trainInfo:trainInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TrainInfoDO> trainInfoList = trainInfoService.list(query);
		int total = trainInfoService.count(query);
		PageUtils pageUtils = new PageUtils(trainInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:trainInfo:add")
	String add(Model model){
		Map<String, Object> params = new HashMap<String, Object>();
		List<TrainTypeDO> trainTypeList = trainTypeService.list(params);
		model.addAttribute("trainTypeList", trainTypeList);
	    return "oa/trainInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:trainInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TrainInfoDO trainInfo = trainInfoService.get(id);
		model.addAttribute("trainInfo", trainInfo);
		Map<String, Object> params = new HashMap<String, Object>();
		List<TrainTypeDO> trainTypeList = trainTypeService.list(params);
		for(TrainTypeDO tt : trainTypeList){
			if(tt.getId()!=null && tt.getId().equals(trainInfo.getFkTypeId())){
				tt.setSfxz("abc");
			}
		}
		model.addAttribute("trainTypeList", trainTypeList);
	    return "oa/trainInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:trainInfo:add")
	public R save(@RequestParam("coverImgFile") MultipartFile coverImgFile,@RequestParam("trainVideoFile") MultipartFile trainVideoFile,TrainInfoDO trainInfo){
		String fileName = "",fileUrl = "";
		//技能培训封面
		if(coverImgFile!=null && !coverImgFile.isEmpty()){
			System.out.println("coverImgFile.getContentType():::::"+coverImgFile.getContentType());
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
			fileUrl =FileUtil.reUrl("trainImg");
			try {
				FileUtil.uploadFile(coverImgFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
				System.out.println("filepath:::::"+bootdoConfig.getUploadPath()+fileUrl+System.getProperty("file.separator")+fileName);
				System.out.println("CheckFileFormatUtil::::"+CheckFileFormatUtil.getFileType(bootdoConfig.getUploadPath()+fileUrl+System.getProperty("file.separator")+fileName));
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
			trainInfo.setCoverImg("/files/"+fileUrl+fileName);
		}
		//技能培训视频
		if(trainVideoFile!=null && !trainVideoFile.isEmpty()){
			fileName = trainVideoFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=2) {
				return R.error(1001, "视频类型错误，请上传视频文件！");
			}
			// 最大100M
			if (trainVideoFile.getSize() > (100 * 1024 * 1024)) {
				return R.error(1002, "视频大小错误，请上传100M大小以内的视频！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("trainVideo");
			try {
				FileUtil.uploadFile(trainVideoFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
			} catch (Exception e) {
				return R.error();
			}
			trainInfo.setTrainVideo("/files/"+fileUrl+fileName);
		} else if(trainInfo.getTrainVideoUrl()!=null && !"".equals(trainInfo.getTrainVideoUrl())){
			trainInfo.setTrainVideo(trainInfo.getTrainVideoUrl());
		} else {
			return R.error(1003, "请上传视频！");
		}
		if(trainInfoService.save(trainInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:trainInfo:edit")
	public R update(@RequestParam("coverImgFile") MultipartFile coverImgFile,@RequestParam("trainVideoFile") MultipartFile trainVideoFile,TrainInfoDO trainInfo){
		String fileName = "",fileUrl = "";
		//技能培训封面
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
			fileUrl =FileUtil.reUrl("trainImg");
			try {
				FileUtil.uploadFile(coverImgFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
			} catch (Exception e) {
				return R.error();
			}
			trainInfo.setCoverImg("/files/"+fileUrl+fileName);
		}
		//技能培训视频
		if(trainVideoFile!=null && !trainVideoFile.isEmpty()){
			fileName = trainVideoFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=2) {
				return R.error(1001, "视频类型错误，请上传视频文件！");
			}
			// 最大100M
			if (trainVideoFile.getSize() > (100 * 1024 * 1024)) {
				return R.error(1002, "视频大小错误，请上传100M大小以内的视频！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("trainVideo");
			try {
				FileUtil.uploadFile(trainVideoFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
			} catch (Exception e) {
				return R.error();
			}
			trainInfo.setTrainVideo("/files/"+fileUrl+fileName);
		} else if(trainInfo.getTrainVideoUrl()!=null && !"".equals(trainInfo.getTrainVideoUrl())){
			trainInfo.setTrainVideo(trainInfo.getTrainVideoUrl());
		} else if(trainInfo.getTrainVideoShow()!=null && !"".equals(trainInfo.getTrainVideoShow())){
			trainInfo.setTrainVideo(trainInfo.getTrainVideoShow());
		} else {
			return R.error(1003, "请上传视频！");
		}
		trainInfoService.update(trainInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:trainInfo:remove")
	public R remove( Integer id){
		if(trainInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:trainInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		trainInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
