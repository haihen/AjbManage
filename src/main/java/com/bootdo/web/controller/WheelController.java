package com.bootdo.web.controller;

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

import com.bootdo.web.domain.WheelDO;
import com.bootdo.web.service.WheelService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * InnoDB free: 9216 kB
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-05 09:17:50
 */
 
@Controller
@RequestMapping("/web/wheel")
public class WheelController {
	@Autowired
	private WheelService wheelService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	@RequiresPermissions("web:wheel:wheel")
	String Wheel(){
	    return "web/wheel/wheel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("web:wheel:wheel")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WheelDO> wheelList = wheelService.list(query);
		int total = wheelService.count(query);
		PageUtils pageUtils = new PageUtils(wheelList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("web:wheel:add")
	String add(){
	    return "web/wheel/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:wheel:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		WheelDO wheel = wheelService.get(id);
		model.addAttribute("wheel", wheel);
	    return "web/wheel/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:wheel:add")
	public R save(@RequestParam("wheelImgFile") MultipartFile wheelImgFile,WheelDO wheel){
		String fileName = "",fileUrl = "";
		//缩略图
		if(wheelImgFile!=null && !wheelImgFile.isEmpty()){
			fileName = wheelImgFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=0) {
				return R.error(1001, "图片类型错误，请上传图片文件！");
			}
			// 最大4m
			if (wheelImgFile.getSize() > (4 * 1024 * 1024)) {
				return R.error(1002, "图片大小错误，请上传4M大小以内的图片！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("wheelImg");
			try {
				FileUtil.uploadFile(wheelImgFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
			} catch (Exception e) {
				return R.error();
			}
			wheel.setImgUrl("/files/"+fileUrl+fileName);
		}
		
		if(wheelService.save(wheel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:wheel:edit")
	public R update(@RequestParam("wheelImgFile") MultipartFile wheelImgFile,WheelDO wheel){
		String fileName = "",fileUrl = "";
		//缩略图
		if(wheelImgFile!=null && !wheelImgFile.isEmpty()){
			fileName = wheelImgFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=0) {
				return R.error(1001, "图片类型错误，请上传图片文件！");
			}
			// 最大4m
			if (wheelImgFile.getSize() > (4 * 1024 * 1024)) {
				return R.error(1002, "图片大小错误，请上传4M大小以内的图片！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("wheelImg");
			try {
				FileUtil.uploadFile(wheelImgFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
			} catch (Exception e) {
				return R.error();
			}
			wheel.setImgUrl("/files/"+fileUrl+fileName);
		}
		
		wheelService.update(wheel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("web:wheel:remove")
	public R remove( Integer id){
		if(wheelService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:wheel:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		wheelService.batchRemove(ids);
		return R.ok();
	}
	
}
