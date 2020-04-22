package com.ajb.news.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ajb.news.domain.NewsDO;
import com.ajb.news.service.NewsService;
import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.common.config.BootdoConfig;
import com.ajb.common.utils.CheckFileFormatUtil;
import com.ajb.common.utils.FileType;
import com.ajb.common.utils.FileUtil;
import com.ajb.common.utils.PageUtils;
import com.ajb.common.utils.Query;
import com.ajb.common.utils.R;
import com.ajb.home.domain.HomeDO;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@CrossOrigin
@RequestMapping("/system/news")
public class NewsController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	
	@RequestMapping()
	@RequiresPermissions("system:news:news")
	String News(){
	    return "system/news/news";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("system:news:news")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NewsDO> newsList = newsService.list(query);
		int total = newsService.count(query);
		PageUtils pageUtils = new PageUtils(newsList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public Map<String,List<NewsDO>> getList(@RequestParam Map<String, Object> params){
		Map<String,List<NewsDO>> map = new HashMap<String, List<NewsDO>>();
		
		List<NewsDO> newsList = newsService.list(params);
		map.put("newsList", newsList);
		
		return map;
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("system:news:add")
	String add(Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", "新闻中心");
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
		String[] menuList = new String[ajbTypeList.size()];
		for(int i=0;i<ajbTypeList.size();i++){
			menuList[i] = ajbTypeList.get(i).getName();
		}
//		String[] menuList = {"安居要闻","下属企业动态","行业发展咨询","政策发布"};
		model.addAttribute("menuList",menuList);
	    return "system/news/add";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("system:news:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		NewsDO news = newsService.get(id);
		model.addAttribute("news", news);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", "新闻中心");
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(map);
		String[] menuList = new String[ajbTypeList.size()];
		for(int i=0;i<ajbTypeList.size();i++){
			menuList[i] = ajbTypeList.get(i).getName();
		}
		model.addAttribute("menuList",menuList);
	    return "system/news/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("system:news:add")
	public R save(@RequestParam("imageFile") MultipartFile imageFile,NewsDO news){
		
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
			fileUrl =FileUtil.reUrl("newsImg");
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
			news.setImageUrl("/files/"+fileUrl+fileName);
		}
		if(newsService.save(news)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:news:edit")
	public R update(@RequestParam("imageFile") MultipartFile imageFile,NewsDO news){
		
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
			fileUrl =FileUtil.reUrl("newsImg");
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
			news.setImageUrl("/files/"+fileUrl+fileName);
		}
		newsService.update(news);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:news:remove")
	public R remove( Integer id){
		if(newsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
}
