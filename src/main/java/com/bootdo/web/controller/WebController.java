package com.bootdo.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.redis.shiro.JedisUtils;
import com.bootdo.common.utils.EncriptUtil;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.UserUtils;
import com.bootdo.oa.domain.ActivityInfoDO;
import com.bootdo.oa.domain.ActivityTypeDO;
import com.bootdo.oa.domain.NoticeInfoDO;
import com.bootdo.oa.domain.NoticeTypeDO;
import com.bootdo.oa.domain.OldEducationDO;
import com.bootdo.oa.domain.SchoolEducationDO;
import com.bootdo.oa.domain.SchoolEducationInfoDO;
import com.bootdo.oa.domain.TrainInfoDO;
import com.bootdo.oa.domain.TrainTypeDO;
import com.bootdo.oa.service.ActivityInfoService;
import com.bootdo.oa.service.ActivityTypeService;
import com.bootdo.oa.service.NoticeInfoService;
import com.bootdo.oa.service.NoticeTypeService;
import com.bootdo.oa.service.OldEducationService;
import com.bootdo.oa.service.SchoolEducationInfoService;
import com.bootdo.oa.service.SchoolEducationService;
import com.bootdo.oa.service.TrainInfoService;
import com.bootdo.oa.service.TrainTypeService;
import com.bootdo.web.domain.ActivityDO;
import com.bootdo.web.domain.WebUserDO;
import com.bootdo.web.domain.WheelDO;
import com.bootdo.web.service.ActivityService;
import com.bootdo.web.service.WebUserService;
import com.bootdo.web.service.WheelService;

import redis.clients.jedis.Jedis;


/**
 * 网站页面
 * 
 * @date 2019-03-26 15:02:00
 */
 
@Controller
@RequestMapping("/home")
@SessionAttributes("webUser")
@CrossOrigin
public class WebController {
	
	@Autowired
	private OldEducationService oldEducationService;
	@Autowired
	private SchoolEducationService schoolEducationService;
	@Autowired
	private SchoolEducationInfoService schoolEducationInfoService;
	@Autowired
	private ActivityTypeService activityTypeService;
	@Autowired
	private ActivityInfoService activityInfoService;
	@Autowired
	private NoticeTypeService noticeTypeService;
	@Autowired
	private NoticeInfoService noticeInfoService;
	@Autowired
	private TrainTypeService trainTypeService;
	@Autowired
	private TrainInfoService trainInfoService;
	@Autowired
	private WebUserService userService;
	@Autowired
	private WheelService wheelService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping("")
	public String index(Model model){
		
		//通知公告
		Map<String, Object> tzggMap = new HashMap<String, Object>();
		//特色活动
		Map<String, Object> tshdMap = new HashMap<String, Object>();
		//轮播图
		Map<String, Object> lbtMap = new HashMap<String, Object>();
		
		tshdMap.put("limit", 5);
		tshdMap.put("offset",0);
		List<ActivityInfoDO> tshdList = activityInfoService.list(tshdMap);
		
		tzggMap.put("limit", 5);
		tzggMap.put("offset",0);
		List<NoticeInfoDO> tzggList = noticeInfoService.list(tzggMap);
		
		List<WheelDO> lbtList = wheelService.list(lbtMap);
		
		model.addAttribute("tshdList", tshdList);
		model.addAttribute("tzggList", tzggList);
		model.addAttribute("lbtList", lbtList);
		
		return "home";
	}
	
	@GetMapping("/head")
	public String head(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "head";
	}
	
	@GetMapping("/lnjy")
	public String lnjy(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "lnjy/lnjy";
	}
	
	@GetMapping("/xljy")
	public String xljy(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		String fkType1 = "",fkType2 = "";
		String fkTypeId1 = "",fkTypeId2 = "";
		if(params.get("fkType1")!=null){
			fkType1 = params.get("fkType1").toString();
		}
		if(params.get("fkType2")!=null){
			fkType2 = params.get("fkType2").toString();
		}
		if(params.get("fkTypeId1")!=null){
			fkTypeId1 = params.get("fkTypeId1").toString();
		}
		if(params.get("fkTypeId2")!=null){
			fkTypeId2 = params.get("fkTypeId2").toString();
		}
		params.put("level", 1);
		List<SchoolEducationDO> schoolEducationList1 = schoolEducationService.list(params);
		params.put("level", 2);
		List<SchoolEducationDO> schoolEducationList2 = schoolEducationService.list(params);
		for(SchoolEducationDO sd1 : schoolEducationList1){
			for(SchoolEducationDO sd2 : schoolEducationList2){
				if(sd1.getId().equals(sd2.getPid())){
					sd1.getChildList().add(sd2);
				}
			}
		}
		model.addAttribute("schoolEducationList", schoolEducationList1);

		String offset = "0";
		if(params.get("offset")!=null){
			offset = params.get("offset").toString();
		}
		if(offset.indexOf("-")>-1){
			offset = "0";
		}
		params.put("limit", 10);
		params.put("offset", offset);
		params.put("fkTypeId1", fkTypeId1);
		params.put("fkTypeId2", fkTypeId2);
		Query query = new Query(params);
		List<SchoolEducationInfoDO> xljyList = schoolEducationInfoService.list(query);
		int count =schoolEducationInfoService.count(query);
		model.addAttribute("xljyList", xljyList);
		model.addAttribute("fkTypeId1", fkTypeId1);
		model.addAttribute("fkTypeId2", fkTypeId2);
		model.addAttribute("fkType1", fkType1);
		model.addAttribute("fkType2", fkType2);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		return "xljy/xljy";
	}
	
	@GetMapping("/xljy_detail")
	public String xljy_detail(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		int id = 0;
		String fkType1 = "",fkType2 = "";
		String fkTypeId1 = "",fkTypeId2 = "";
		if(params.get("fkType1")!=null){
			fkType1 = params.get("fkType1").toString();
		}
		if(params.get("fkType2")!=null){
			fkType2 = params.get("fkType2").toString();
		}
		if(params.get("fkTypeId1")!=null){
			fkTypeId1 = params.get("fkTypeId1").toString();
		}
		if(params.get("fkTypeId2")!=null){
			fkTypeId2 = params.get("fkTypeId2").toString();
		}
		if(params.get("id")!=null){
			id = Integer.parseInt(params.get("id").toString());
		}
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("level", 1);
		List<SchoolEducationDO> schoolEducationList1 = schoolEducationService.list(params2);
		params2.put("level", 2);
		List<SchoolEducationDO> schoolEducationList2 = schoolEducationService.list(params2);
		for(SchoolEducationDO sd1 : schoolEducationList1){
			for(SchoolEducationDO sd2 : schoolEducationList2){
				if(sd1.getId().equals(sd2.getPid())){
					sd1.getChildList().add(sd2);
				}
			}
		}
		model.addAttribute("schoolEducationList", schoolEducationList1);
		SchoolEducationInfoDO xljy = schoolEducationInfoService.get(id);
		fkTypeId1 = xljy.getFkTypeId1()+"";
		fkType1 = xljy.getFkType1();
		fkTypeId2 = xljy.getFkTypeId2()+"";
		fkType2 = xljy.getFkType2();
		SchoolEducationInfoDO adjacent = schoolEducationInfoService.getAdjacent(id);
		model.addAttribute("xljy", xljy);
		model.addAttribute("adjacent", adjacent);
		model.addAttribute("fkTypeId1", fkTypeId1);
		model.addAttribute("fkTypeId2", fkTypeId2);
		model.addAttribute("fkType1", fkType1);
		model.addAttribute("fkType2", fkType2);
		return "xljy/xljy_detail";
	}
	
	@GetMapping("/lnjy_zxzx")
	public String lnjy_zxzx(HttpServletRequest request,Model model){
		initWebUser(request,model);
		Map<String, Object> params = new HashMap<String, Object>();
		//通知公告
		params.put("type", "资讯中心-通知公告");
		List<OldEducationDO> tzggList = oldEducationService.list(params);
		model.addAttribute("tzggList", tzggList);
		//教学动态
		params.put("type", "资讯中心-教学动态");
		List<OldEducationDO> jxdtList = oldEducationService.list(params);
		model.addAttribute("jxdtList", jxdtList);
		//特色生活
		params.put("type", "资讯中心-特色生活");
		List<OldEducationDO> tsshList = oldEducationService.list(params);
		model.addAttribute("tsshList", tsshList);
		//学员风采
		params.put("type", "资讯中心-学员风采");
		List<OldEducationDO> xyfcList = oldEducationService.list(params);
		model.addAttribute("xyfcList", xyfcList);
				
		return "lnjy/lnjy_zxzx";
	}
	
	@GetMapping("/lnjy_zxzx_list")
	public String lnjy_zxzx_list(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		String type = "资讯中心-通知公告",offset = "0";
		if(params.get("type")!=null){
			type = params.get("type").toString();
		}
		if(params.get("offset")!=null){
			offset = params.get("offset").toString();
		}
		if(offset.indexOf("-")>-1){
			offset = "0";
		}
		params.put("limit", 5);
		params.put("offset", offset);
		params.put("type", type);
		Query query = new Query(params);
		List<OldEducationDO> zxzxList = oldEducationService.list(query);
		int count =oldEducationService.count(query);
		model.addAttribute("zxzxList", zxzxList);
		model.addAttribute("type", type);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		return "lnjy/lnjy_zxzx_list";
	}
	
	@GetMapping("/lnjy_zxzx_detail")
	public String lnjy_zxzx_detail(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		String type = "";
		int id = 0;
		if(params.get("type")!=null){
			type = params.get("type").toString();
		}
		if(params.get("id")!=null){
			id = Integer.parseInt(params.get("id").toString());
		}
		OldEducationDO zxzx = oldEducationService.get(id);
		OldEducationDO adjacent = oldEducationService.getAdjacent(id);
		model.addAttribute("zxzx", zxzx);
		model.addAttribute("adjacent", adjacent);
		model.addAttribute("type", zxzx.getType());
		return "lnjy/lnjy_zxzx_detail";
	}
	
	@GetMapping("/lnjy_other")
	public String lnjy_other(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		String type = "健康养生",offset = "0";
		if(params.get("type")!=null){
			type = params.get("type").toString();
		}
		if(params.get("offset")!=null){
			offset = params.get("offset").toString();
		}
		if(offset.indexOf("-")>-1){
			offset = "0";
		}
		params.put("limit", 4);
		params.put("offset", offset);
		params.put("type", type);
		Query query = new Query(params);
		List<OldEducationDO> otherList = oldEducationService.list(query);
		int count =oldEducationService.count(query);
		model.addAttribute("otherList", otherList);
		model.addAttribute("type", type);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		return "lnjy/lnjy_other";
	}
	
	@GetMapping("/getZxzxList")
	@ResponseBody
	public PageUtils getZxzxList(HttpServletRequest request,@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OldEducationDO> zxzxInfoList = oldEducationService.list(query);
		int total = oldEducationService.count(query);
		PageUtils pageUtils = new PageUtils(zxzxInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/dzts")
	public String dzts(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "dzts/dzts";
	}
	
	@GetMapping("/dzts_qk")
	public String dzts_qk(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "dzts/dzts_qk";
	}
	
	@GetMapping("/dzts_bz")
	public String dzts_bz(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "dzts/dzts_bz";
	}
	
	@GetMapping("/spkc")
	public String spkc(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "spkc/spkc";
	}
	
	@GetMapping("/spkc_ysdw")
	public String spkc_ysdw(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "spkc/spkc_ysdw";
	}
	
	@GetMapping("/spkc_wsp")
	public String spkc_wsp(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "spkc/spkc_wsp";
	}
	//特色活动列表页
	@GetMapping("/tshd")
	public String tshd(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		String fkType = "",fkTypeId = "";
		if(params.get("fkType")!=null){
			fkType = params.get("fkType").toString();
		}
		if(params.get("fkTypeId")!=null){
			fkTypeId = params.get("fkTypeId").toString();
		}
		List<ActivityTypeDO> activityTypeList = activityTypeService.list(params);

		model.addAttribute("activityTypeList", activityTypeList);

		String offset = "0";
		if(params.get("offset")!=null){
			offset = params.get("offset").toString();
		}
		if(offset.indexOf("-")>-1){
			offset = "0";
		}
		params.put("limit", 10);
		params.put("offset", offset);
		params.put("fkTypeId", fkTypeId);
		Query query = new Query(params);
		List<ActivityInfoDO> tshdList = activityInfoService.list(query);
		int count =activityInfoService.count(query);
		model.addAttribute("tshdList", tshdList);
		model.addAttribute("fkTypeId", fkTypeId);
		model.addAttribute("fkType", fkType);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		return "tshd/tshd";
	}
	//特色活动详细页
	@GetMapping("/tshd_detail")
	public String tshd_detail(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		int id = 0;
		String fkType = "",fkTypeId = "";
		if(params.get("fkType")!=null){
			fkType = params.get("fkType").toString();
		}

		if(params.get("fkTypeId")!=null){
			fkTypeId = params.get("fkTypeId").toString();
		}

		if(params.get("id")!=null){
			id = Integer.parseInt(params.get("id").toString());
		}
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		List<ActivityTypeDO> activityTypeList = activityTypeService.list(params2);
		model.addAttribute("activityTypeList", activityTypeList);
		ActivityInfoDO tshd = activityInfoService.get(id);
		fkTypeId = tshd.getFkTypeId()+"";
		fkType = tshd.getFkType();
		ActivityInfoDO adjacent = activityInfoService.getAdjacent(id);
		model.addAttribute("tshd", tshd);
		model.addAttribute("adjacent", adjacent);
		model.addAttribute("fkTypeId", fkTypeId);
		model.addAttribute("fkType", fkType);
		return "tshd/tshd_detail";
	}
	//通知公告列表页
	@GetMapping("/tzgg")
	public String tzgg(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		String fkType = "",fkTypeId = "";
		if(params.get("fkType")!=null){
			fkType = params.get("fkType").toString();
		}
		if(params.get("fkTypeId")!=null){
			fkTypeId = params.get("fkTypeId").toString();
		}
		List<NoticeTypeDO> noticeTypeList = noticeTypeService.list(params);

		model.addAttribute("noticeTypeList", noticeTypeList);

		String offset = "0";
		if(params.get("offset")!=null){
			offset = params.get("offset").toString();
		}
		if(offset.indexOf("-")>-1){
			offset = "0";
		}
		params.put("limit", 5);
		params.put("offset", offset);
		params.put("fkTypeId", fkTypeId);
		Query query = new Query(params);
		List<NoticeInfoDO> tzggList = noticeInfoService.list(query);
		int count = noticeInfoService.count(query);
		model.addAttribute("tzggList", tzggList);
		model.addAttribute("fkTypeId", fkTypeId);
		model.addAttribute("fkType", fkType);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		return "tzgg/tzgg";
	}
	//通知公告详细页
	@GetMapping("/tzgg_detail")
	public String tzgg_detail(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		int id = 0;
		String fkType = "",fkTypeId = "";
		if(params.get("fkType")!=null){
			fkType = params.get("fkType").toString();
		}

		if(params.get("fkTypeId")!=null){
			fkTypeId = params.get("fkTypeId").toString();
		}

		if(params.get("id")!=null){
			id = Integer.parseInt(params.get("id").toString());
		}
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		List<NoticeTypeDO> noticeTypeList = noticeTypeService.list(params2);
		model.addAttribute("noticeTypeList", noticeTypeList);
		NoticeInfoDO tzgg = noticeInfoService.get(id);
		fkTypeId = tzgg.getFkTypeId()+"";
		fkType = tzgg.getFkType();
		NoticeInfoDO adjacent = noticeInfoService.getAdjacent(id);
		model.addAttribute("tzgg", tzgg);
		model.addAttribute("adjacent", adjacent);
		model.addAttribute("fkTypeId", fkTypeId);
		model.addAttribute("fkType", fkType);
		return "tzgg/tzgg_detail";
	}
	//技能培训列表页
	@GetMapping("/jnpx")
	public String jnpx(HttpServletRequest request,@RequestParam Map<String, Object> params,Model model){
		initWebUser(request,model);
		String fkType = "",fkTypeId = "";
		if(params.get("fkType")!=null){
			fkType = params.get("fkType").toString();
		}
		if(params.get("fkTypeId")!=null){
			fkTypeId = params.get("fkTypeId").toString();
		}
		List<TrainTypeDO> trainTypeList = trainTypeService.list(params);

		model.addAttribute("trainTypeList", trainTypeList);

		String offset = "0";
		if(params.get("offset")!=null){
			offset = params.get("offset").toString();
		}
		if(offset.indexOf("-")>-1){
			offset = "0";
		}
		params.put("limit", 8);
		params.put("offset", offset);
		params.put("fkTypeId", fkTypeId);
		Query query = new Query(params);
		List<TrainInfoDO> jnpxList = trainInfoService.list(query);
		int count = trainInfoService.count(query);
		model.addAttribute("jnpxList", jnpxList);
		model.addAttribute("fkTypeId", fkTypeId);
		model.addAttribute("fkType", fkType);
		model.addAttribute("count", count);
		model.addAttribute("offset", offset);
		return "jnpx/jnpx";
	}
	
	@GetMapping("/grkj")
	public String grkj(HttpServletRequest request,Model model){
		initWebUser(request,model);
		String viewName = "grkj/grkj";
		
		if(request.getSession().getAttribute("webUser")==null){
			viewName = "login/login";
		} else {
			WebUserDO webUserDo = new WebUserDO();
			webUserDo = (WebUserDO)request.getSession().getAttribute("webUser");
			webUserDo = userService.get(webUserDo.getUserId());
			model.addAttribute("webUser", webUserDo);
		}
		return viewName;
	}
	
	@GetMapping("/regist")
	public String regist(HttpServletRequest request,Model model){
//		initWebUser(request,model);
		return "login/regist";
	}
	
	@GetMapping("/registMobile")
	public String registMobile(HttpServletRequest request,Model model){
//		initWebUser(request,model);
		return "login/registMobile";
	}
	
	@GetMapping("/points")
	public String points(HttpServletRequest request,Model model){
//		initWebUser(request,model);
		return "points";
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest request,Model model){
		initWebUser(request,model);
		return "login/login";
	}
	/**
	 * 网站登录
	 */
	@ResponseBody
	@PostMapping("/loginWeb")
	public R loginWeb( WebUserDO user,HttpServletRequest request,ModelMap map){
		Map<String, Object> params = new HashMap<String, Object>();
		if(TextUtils.isEmpty(user.getLoginName())){
			return R.error(2,"请输入用户名！");
		}
		if(TextUtils.isEmpty(user.getPassword())){
			return R.error(2,"请输入密码！");
		}
		System.out.println("user.getPassword():::"+user.getPassword());
		String password = user.getPassword();
		password = EncriptUtil.MD5(user.getPassword());
		user.setPassword(password);
		
		System.out.println("password:::"+password);
		Integer errNum = 0;
		errNum = UserUtils.getUserLockNum(user.getLoginName());
//		JedisUtils jedisUtils = new JedisUtils();
//		Jedis jedis = jedisUtils.getJedis();
//		String errNumStr = "0";
//		Integer errNum = 0;
//		
//		errNumStr = jedis.get("LOCK"+user.getLoginName());
//		if(TextUtils.isEmpty(errNumStr)){
//			errNum = 0;
//		} else {
//			errNum = Integer.parseInt(errNumStr);
//		}
		
		//登录错误或修改密码错误超过5次（包含5次），账号被锁定
		if(errNum==-1){
			return R.error(2,"系统繁忙，请不要频繁操作！");
		}
		if(errNum>=5){
			return R.error(2,"您的账户因多次操作错误已被锁定，锁定时长为1个小时！");
		}
		params.put("loginName", user.getLoginName());
		params.put("password", password);
		WebUserDO webUser = new WebUserDO();
		webUser = userService.getByLogin(params);
		int code = 0;
		if(webUser!=null){
			//登陆一次增加一个积分
			userService.updatePointsWeb(webUser);
			request.getSession().setAttribute("webUser", webUser);
			map.put("webUser", webUser);
			code = UserUtils.setUserLockNum(user.getLoginName(),true);
			if(code==-1){
				return R.error(2,"系统繁忙，请不要频繁操作！");
			}
			return R.ok();
		} else {
			//登录错误添加错误次数
			code = UserUtils.setUserLockNum(user.getLoginName(),false);
			if(code==-1){
				return R.error(2,"系统繁忙，请不要频繁操作！");
			}
		}
		return R.error(2,"登录失败，用户不存在或密码错误！");
	}

	/**
	 * 网站保存
	 */
	@ResponseBody
	@CrossOrigin
	@PostMapping("/saveUserWeb")
	public R saveWeb(HttpServletRequest request,WebUserDO user){
		String inType = "";
		inType = user.getInType();
		if(inType==null || "".equals(inType)){
			return R.error(2,"未获取到用户注册入口！");
		}

		//验证手机验证码
		String smsCode = "";
		//APP端
		if("APP".equals(inType)){
			String smsCodeInput = "";
			smsCode = user.getSmsCode();
			smsCodeInput = user.getSmsCodeInput();
			if(smsCodeInput==null || "".equals(smsCodeInput)){
				return R.error(2,"未填写手机验证码！");
			}
			if(smsCode!=null && !"".equals(smsCode)){
				if(!smsCodeInput.equals(smsCode)){
					return R.error(2,"您输入的手机验证码错误！");
				}
			} else {
				return R.error(2,"未获取到手机验证码！");
			}
		} 
		//非APP端
		else {
			Object smsObject = request.getSession().getAttribute(user.getLoginName());
			System.out.println("smsObject:::"+smsObject);
			if(smsObject!=null){
				smsCode = request.getSession().getAttribute(user.getLoginName())+"";
				System.out.println("smsCode:::"+smsCode);
				if(smsCode!=null && !"".equals(smsCode)){
					if(!smsCode.equals(user.getSmsCode())){
						return R.error(2,"您输入的手机验证码错误！");
					}
				} else {
					return R.error(2,"未获取到手机验证码2！");
				}
			} else {
				return R.error(2,"未获取到手机验证码1！");
			}
		}

//		String password = user.getPassword();
//		password = EncriptUtil.MD5(password);
//		user.setPassword(password);
		
		if(userService.save(user)>0){
			if("APP".equals(inType)){
				//APP用户注册写入PC端数据库
				return R.ok();
			} else {
				//PC、微信端用户注册写入APP端数据库
				String enc = "",uname = "";
				uname = user.getLoginName();
//				password = user.getPassword();
				enc = MD5Utils.getAppEnc(uname);
				return R.ok().put("uname", uname).put("password", user.getPassword()).put("enc", enc);
			}
		} else {
			if("APP".equals(inType)){
				//APP用户注册写入PC端数据库
				return R.ok();
			}
		}
		return R.error(2,"注册用户失败！");
	}
	
	/**
	 * 修改个人信息
	 */
	@ResponseBody
	@RequestMapping("/updateUserWeb")
	public R updateUserWeb(HttpServletRequest request,@RequestParam("headImageFile") MultipartFile headImageFile,WebUserDO user){
		String fileName = "",fileUrl = "";
		//技能培训封面
		if(headImageFile!=null && !headImageFile.isEmpty()){
			fileName = headImageFile.getOriginalFilename();
			// 验证文件类型
			if (FileType.fileType(fileName)!=0) {
				return R.error(1001, "图片类型错误，请上传图片文件！");
			}
			// 最大4m
			if (headImageFile.getSize() > (4 * 1024 * 1024)) {
				return R.error(1002, "图片大小错误，请上传4M大小以内的图片！");
			}
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl =FileUtil.reUrl("headImg");
			try {
				FileUtil.uploadFile(headImageFile.getBytes(), bootdoConfig.getUploadPath()+fileUrl, fileName);
			} catch (Exception e) {
				return R.error();
			}
			user.setHeadImage("/files/"+fileUrl+fileName);
		}
		//验证修改后的手机号是否重复
		int count = 0;
		String loginName = "";
		Map<String, Object> map = new HashMap<String, Object>();
		loginName = user.getMobile();
		map.put("loginName", loginName);
		map.put("userId", user.getUserId());
		count = userService.countByLogin(map);
		if(count>0){
			return R.error(1003, "修改后的手机号已注册！");
		}
		System.out.println("loginName----"+loginName);
		System.out.println("user.getLoginName()----"+user.getLoginName());
		//验证手机验证码, 手机号变更后验证
		if(!loginName.equals(user.getLoginName())){
			String smsCode = "";
			Object smsObject = request.getSession().getAttribute(user.getMobile());
			if(smsObject!=null){
				smsCode = request.getSession().getAttribute(user.getMobile())+"";
				if(smsCode!=null && !"".equals(smsCode)){
					if(!smsCode.equals(user.getSmsCode())){
						return R.error(2,"您输入的手机验证码错误！");
					}
				} else {
					return R.error(2,"未获取到手机验证码！");
				}
			} else {
				return R.error(2,"未获取到手机验证码！");
			}
		}

		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 修改个人密码
	 */
	@ResponseBody
	@RequestMapping("/updateMMWeb")
	public R updateMMWeb(HttpServletRequest request,WebUserDO user){

		return userService.updateMMWeb(user);
	}
	
	@ResponseBody
	@GetMapping("/listActivity")
	public R listActivity(HttpServletRequest request){
		//查询列表数据
		List<ActivityDO> aList = new ArrayList<ActivityDO>();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		aList = activityService.listPlay(params);
		map.put("list", aList);
		return R.ok(map);
	}

	public void initWebUser(HttpServletRequest request,Model model){
		WebUserDO webUserDo = new WebUserDO();
		if(request.getSession().getAttribute("webUser")!=null){
			webUserDo = (WebUserDO)request.getSession().getAttribute("webUser");
		} else {
			webUserDo = null;
		}
		System.out.println("initsession----"+request.getSession().getId());
		System.out.println("789..."+webUserDo);
		model.addAttribute("webUser", webUserDo);
	}
	
	/**
	 * 将所有人密码加密
	 */
	@ResponseBody
	@RequestMapping("/updateAllMM")
	public R updateAllMM(HttpServletRequest request){
		userService.updateAllMM();
		return R.ok();
	}
}
