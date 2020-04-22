package com.ajb.science.controller;

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

import com.ajb.science.domain.ScienceDO;
import com.ajb.science.service.ScienceService;
import com.ajb.system.config.RequestState;
import com.ajb.system.config.ResultMessage;
import com.ajb.work.domain.WorkDO;
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
import com.ajb.log.MyLog;
import com.ajb.news.domain.NewsDO;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@CrossOrigin
@RequestMapping("/web/science")
public class WebScienceController {
	@Autowired
	private ScienceService scienceService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	@Autowired
	private AjbTypeImageService ajbTypeImageService;

	@MyLog(value="科技创新列表")
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String type = params.get("type")+"";
		
		if(!"科技创新".equals(type)){
			if("全部列表".equals(type)){
				params.put("type", null);
			}
			params.put("isShow", 1);
			List<ScienceDO> scienceList = scienceService.list(params);
			map.put("scienceList", scienceList);
			int total = scienceService.count(params);
			map.put("total", total);
		}
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("type", "科技创新");
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
	
	@MyLog(value="科技创新详情")
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
			ScienceDO detail = scienceService.get(Integer.parseInt(id));
			map.put("detail", detail);
			
			message.setMessage("查询成功");
			message.setStatus(RequestState.SUCCESS);
			message.setResultMap(map);
		}
		
		return message;
	}	
}
