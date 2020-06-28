package com.ajb.work.controller;

import java.io.File;
import java.util.ArrayList;
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

import com.ajb.work.domain.WorkDO;
import com.ajb.work.service.WorkService;
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
import com.ajb.party.domain.PartyDO;
import com.ajb.system.config.RequestState;
import com.ajb.system.config.ResultMessage;

/**
 * 
 * @author yuyang
 * @date 2020-01-13 19:11:29
 */
 
@Controller
@CrossOrigin
@RequestMapping("/web/work")
public class WebWorkController {
	@Autowired
	private WorkService workService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private AjbTypeService ajbTypeService;
	@Autowired
	private AjbTypeImageService ajbTypeImageService;

	@MyLog(value="业务领域列表")
	@ResponseBody
	@RequestMapping("/getList")
	public ResultMessage getList(@RequestParam Map<String, Object> params){
		
		ResultMessage message = new ResultMessage();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String type = params.get("type")+"";

		params.put("isShow", 1);
		if(!"业务领域".equals(type)){
			if("房地产开发".equals(type)||
					"市政基础设施".equals(type)||
					"供热民生".equals(type)){
				Query query = new Query(params);
				List<WorkDO> workList = workService.list(query);
				int total = workService.count(query);
				map.put("workList", workList);
				map.put("total", total);
			} else if("物业服务".equals(type)){
				params.put("type", "企业简介");
				List<WorkDO> qyjjList = workService.getNoLimitList(params);
				map.put("qyjjList", qyjjList);
				
				params.put("type", "企业特色");
				List<WorkDO> qytsList = workService.getNoLimitList(params);
				map.put("qytsList", qytsList);
				int qytsTotal = workService.count(params);
				map.put("qytsTotal", qytsTotal);
				
				params.put("type", "体系认证");
				List<WorkDO> txrzList = workService.getNoLimitList(params);
				map.put("txrzList", txrzList);
				
				params.put("type", "体系认证图片");
				List<WorkDO> txrztpList = workService.getNoLimitList(params);
				map.put("txrztpList", txrztpList);
				
				params.put("type", "获奖荣誉");
				List<WorkDO> hjryList = workService.getNoLimitList(params);
				map.put("hjryList", hjryList);
				int hjryTotal = workService.count(params);
				map.put("hjryTotal", hjryTotal);
			} else {
				if("全部列表".equals(type)){
					params.put("type", null);
				}
				List<WorkDO> workList = workService.getNoLimitList(params);
				map.put("workList", workList);
				int total = workService.count(params);
				map.put("total", total);
			}
		}
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("type", "业务领域");
		//模块图片
		List<AjbTypeImageDO> ajbTypeImageList = ajbTypeImageService.list(params2);
		map.put("ajbTypeImageList", ajbTypeImageList);
		//模块类型
//		String[] ajbTypeListArray = {"房地产开发","市政基础设施","商业运营","建筑节能及新技术推广","物业服务","供热民生"};
//		List<AjbTypeDO> ajbTypeList = new ArrayList<AjbTypeDO>();
//		for(String ajbType:ajbTypeListArray){
//			AjbTypeDO ajb = new AjbTypeDO();
//			ajb.setName(ajbType);
//			ajbTypeList.add(ajb);
//		}
//		map.put("ajbTypeList", ajbTypeList);
		List<AjbTypeDO> ajbTypeList = ajbTypeService.list(params2);
		map.put("ajbTypeList", ajbTypeList);

		message.setMessage("查询成功");
		message.setStatus(RequestState.SUCCESS);
		message.setResultMap(map);
		return message;
	}
	
	@MyLog(value="业务领域详情")
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
			WorkDO detail = workService.get(Integer.parseInt(id));
			map.put("detail", detail);
			
			message.setMessage("查询成功");
			message.setStatus(RequestState.SUCCESS);
			message.setResultMap(map);
		}
		
		return message;
	}	
}
