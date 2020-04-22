package com.ajb.party.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ajb.party.domain.PartyDO;
import com.ajb.party.domain.PartyItemDO;
import com.ajb.party.service.PartyItemService;
import com.ajb.party.service.PartyService;
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
@CrossOrigin
@RequestMapping("/system/party")
public class PartyController {
	@Autowired
	private PartyService partyService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	@Autowired
	private PartyItemService partyItemService;
	
	@RequestMapping()
	@RequiresPermissions("system:party:party")
	String Party(){
	    return "system/party/party";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("system:party:party")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PartyDO> partyList = partyService.list(query);
		int total = partyService.count(query);
		PageUtils pageUtils = new PageUtils(partyList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String type = params.get("type")+"";
		
		if("主题教育".equals(type)){
			List<PartyItemDO> partyItemList = partyItemService.list(params);
			List<PartyDO> partyList = partyService.list(params);
			
			for(PartyItemDO partyItemDO:partyItemList){
				for(PartyDO partyDO:partyList){
					if(partyItemDO.getId().equals(partyDO.getSubTypeId())){
						partyItemDO.getPartyList().add(partyDO);
					}
				}
			}
			map.put("partyItemList", partyItemList);
		} else {
			List<PartyDO> partyList = partyService.list(params);
			map.put("partyList", partyList);
		}
		
		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("system:party:add")
	String add(Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", "党的建设");
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
		String[] menuList = new String[ajbTypeList.size()];
		for(int i=0;i<ajbTypeList.size();i++){
			menuList[i] = ajbTypeList.get(i).getName();
		}
		model.addAttribute("menuList",menuList);
	    return "system/party/add";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("system:party:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PartyDO party = partyService.get(id);
		if(!TextUtils.isEmpty(party.getFileUrl())){
			String fileUrl = party.getFileUrl();
			fileUrl = party.getTitle()+fileUrl.substring(party.getFileUrl().indexOf("."));
			party.setShowFile(fileUrl);
		}
		model.addAttribute("party", party);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", "党的建设");
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
		String[] menuList = new String[ajbTypeList.size()];
		for(int i=0;i<ajbTypeList.size();i++){
			menuList[i] = ajbTypeList.get(i).getName();
		}
		model.addAttribute("menuList",menuList);
	    return "system/party/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("system:party:add")
	public R save(@RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam("partyFile") MultipartFile partyFile,
			PartyDO party){
		
		String fileName = "",fileUrl = "";
		
		String type = "";
		
		type = party.getType();
		
		if(!"党建经验".equals(type)){
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
				fileUrl =FileUtil.reUrl("partyImg");
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
				party.setImageUrl("/files/"+fileUrl+fileName);
			}
		} else {
			if(partyFile!=null && !partyFile.isEmpty()){
				fileName = partyFile.getOriginalFilename();
				// 验证文件类型
				if (FileType.fileType(fileName)!=1) {
					return R.error(1001, "文件类型错误，请上传文件！");
				}
				// 最大10m
				if (partyFile.getSize() > (10 * 1024 * 1024)) {
					return R.error(1002, "文件大小错误，请上传10M大小以内的图片！");
				}
//				fileName = FileUtil.renameToUUID(fileName);
				fileUrl =FileUtil.reUrl("partyImg");
				try {
					FileUtil.uploadFile(partyFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
					String fName = bootdoConfig.getUploadPath()+fileUrl+System.getProperty("file.separator")+fileName;
					boolean isImage = false;
					if(CheckFileFormatUtil.getFileType(fName)!=null){
						isImage = true;
					}
					if(!isImage){
						File imgFile = new File(fName);
						imgFile.delete();
						return R.error(1001, "文件类型错误，请上传文件！");
					}
				} catch (Exception e) {
					return R.error();
				}
				party.setFileUrl("/files/"+fileUrl+fileName);
			}
		}
		
		if(partyService.save(party)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:party:edit")
	public R update(@RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam("partyFile") MultipartFile partyFile,
			PartyDO party){
		
		String fileName = "",fileUrl = "";
		
		String type = "";
		
		type = party.getType();
		
		if(!"党建经验".equals(type)){
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
				fileUrl =FileUtil.reUrl("partyImg");
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
				party.setImageUrl("/files/"+fileUrl+fileName);
			}
		} else {
			if(partyFile!=null && !partyFile.isEmpty()){
				fileName = partyFile.getOriginalFilename();
				// 验证文件类型
				if (FileType.fileType(fileName)!=1) {
					return R.error(1001, "文件类型错误，请上传文件！");
				}
				// 最大10m
				if (partyFile.getSize() > (10 * 1024 * 1024)) {
					return R.error(1002, "文件大小错误，请上传10M大小以内的图片！");
				}
//				fileName = FileUtil.renameToUUID(fileName);
				fileUrl =FileUtil.reUrl("partyImg");
				try {
					FileUtil.uploadFile(partyFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
					String fName = bootdoConfig.getUploadPath()+fileUrl+System.getProperty("file.separator")+fileName;
					boolean isImage = false;
					if(CheckFileFormatUtil.getFileType(fName)!=null){
						isImage = true;
					}
					if(!isImage){
						File imgFile = new File(fName);
						imgFile.delete();
						return R.error(1001, "文件类型错误，请上传文件！");
					}
				} catch (Exception e) {
					return R.error();
				}
				party.setFileUrl("/files/"+fileUrl+fileName);
			}
		}
		partyService.update(party);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:party:remove")
	public R remove( Integer id){
		if(partyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
}
