package com.ajb.news.controller;

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

import com.ajb.news.domain.NewsDO;
import com.ajb.news.service.NewsService;
import com.ajb.system.config.RequestState;
import com.ajb.system.config.ResultMessage;
import com.ajb.ajbtype.domain.AjbTypeDO;
import com.ajb.ajbtype.domain.AjbTypeImageDO;
import com.ajb.ajbtype.service.AjbTypeImageService;
import com.ajb.ajbtype.service.AjbTypeService;
import com.ajb.common.config.BootdoConfig;
import com.ajb.common.utils.CheckFileFormatUtil;
import com.ajb.common.utils.FileType;
import com.ajb.common.utils.FileUtil;
import com.ajb.common.utils.PageUtils;
import com.ajb.common.utils.Query;
import com.ajb.common.utils.R;
import com.ajb.home.domain.HomeDO;
import com.ajb.log.MyLog;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@CrossOrigin
@RequestMapping("/web/news")
public class WebNewsController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	@Autowired
	private AjbTypeImageService ajbTypeImageService;
	
	@MyLog(value="新闻中心列表")
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String type = params.get("type")+"";
		
		if(!"新闻中心".equals(type)){
			params.put("isShow", 1);
			if("全部列表".equals(type)){
				params.put("type", null);
			}
			List<NewsDO> newsList = newsService.list(params);
			map.put("newsList", newsList);
			int total = newsService.count(params);
			map.put("total", total);
		}

		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("type", "新闻中心");
		//模块图片
		List<AjbTypeImageDO> ajbTypeImageList = ajbTypeImageService.list(params2);
		map.put("ajbTypeImageList", ajbTypeImageList);
		//模块类型
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(params2);
		map.put("ajbTypeList", ajbTypeList);
		
		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}
	
	@MyLog(value="新闻中心详情")
	@ResponseBody
	@RequestMapping("/getDetail")
	public ResultMessage getDetail(@RequestParam Map<String, Object> params){
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String id = params.get("id")+"";
		if(TextUtils.isEmpty(id)){
			message.setMessage("查询失败");
			message.setStatus(RequestState.FAIL);
		} else {
			newsService.updateBrowseNum(Integer.parseInt(id));
			NewsDO detail = newsService.get(Integer.parseInt(id));
			map.put("detail", detail);
			
			message.setMessage("查询成功");
			message.setStatus(RequestState.SUCCESS);
			message.setResultMap(map);
		}
		
		return message;
	}
}
