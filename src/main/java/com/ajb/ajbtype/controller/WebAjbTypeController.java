package com.ajb.ajbtype.controller;

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

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@RequestMapping("/web/ajb")
public class WebAjbTypeController {

	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	@Autowired
	private AjbTypeImageService ajbTypeImageService;
	
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		//模块图片
		List<AjbTypeImageDO> ajbTypeImageList = ajbTypeImageService.list(params);
		map.put("ajbTypeImageList", ajbTypeImageList);
		//模块类型
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(params);
		map.put("ajbTypeList", ajbTypeList);
		
		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}
}
