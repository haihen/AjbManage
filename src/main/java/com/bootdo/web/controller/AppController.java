package com.bootdo.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
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
import com.bootdo.web.domain.WebUserDO;
import com.bootdo.web.service.WebUserService;

import redis.clients.jedis.Jedis;


/**
 * 网站页面
 * 
 * @date 2019-03-26 15:02:00
 */
 
@Controller
@RequestMapping("/home")
@CrossOrigin
public class AppController {
	
	@Autowired
	private WebUserService userService;

	/**
	 * 网站登录
	 */
	@ResponseBody
	@RequestMapping("/loginApp")
	public R loginWeb( WebUserDO user,HttpServletRequest request,ModelMap map){
		Map<String, Object> params = new HashMap<String, Object>();
		System.out.println("user.getLoginName()::::"+user.getLoginName());
		System.out.println("user.getPassword()::::"+user.getPassword());
		if(TextUtils.isEmpty(user.getLoginName())){
			return R.error(2,"请输入用户名！");
		}
		if(TextUtils.isEmpty(user.getPassword())){
			return R.error(2,"请输入密码！");
		}
		String password = user.getPassword();
		password = EncriptUtil.MD5(user.getPassword());
		user.setPassword(password);
		Integer errNum = 0;
		errNum = UserUtils.getUserLockNum(user.getLoginName());
		//登录错误或修改密码错误超过5次（包含5次），账号被锁定
		if(errNum>=5){
			return R.error(2,"您的账户因多次操作错误已被锁定，锁定时长为1个小时！");
		}
		params.put("loginName", user.getLoginName());
		params.put("password", password);
		WebUserDO webUser = new WebUserDO();
		webUser = userService.getByLogin(params);
		if(webUser!=null){
			//登陆一次增加一个积分
			userService.updatePointsWeb(webUser);
			UserUtils.setUserLockNum(user.getLoginName(),true);
//			JedisUtils jedisUtils = new JedisUtils();
//			Jedis jedis = jedisUtils.getJedis();
//			String webUserStr = "";
//			webUserStr = JSON.toJSONString(webUser);
//			jedis.set(webUser.getUserId()+"", webUserStr);
			return R.ok().put("webUser", webUser);
		} else {
			//登录错误添加错误次数
			UserUtils.setUserLockNum(user.getLoginName(),false);

		}
		return R.error(2,"登录失败，用户不存在或密码错误！");
	}

	/**
	 * 网站保存
	 */
	@ResponseBody
	@RequestMapping("/saveUserApp")
	public R saveWeb(HttpServletRequest request,WebUserDO user){

		JedisUtils jedisUtils = new JedisUtils();
		Jedis jedis = new Jedis();
		try {
			jedis = jedisUtils.getJedis();
			Properties props = jedisUtils.getProps();
			jedis.select(Integer.parseInt(props.get("login_session").toString()));
			//验证手机验证码
			String smsCode = "";
			smsCode = jedis.get(user.getLoginName());
			if(smsCode!=null && !"".equals(smsCode)){
				if(!smsCode.equals(user.getSmsCode())){
					return R.error(2,"您输入的手机验证码错误！");
				}
			} else {
				return R.error(2,"未获取到手机验证码！");
			}

			if(userService.save(user)>0){
				//PC、微信端用户注册写入APP端数据库
				String enc = "",uname = "",password = "";
				uname = user.getLoginName();
				password = user.getPassword();
				enc = MD5Utils.getAppEnc(uname);
				return R.ok().put("uname", uname).put("password", password).put("enc", enc);
			}
			
		} catch (Exception e) { 
			if(jedis!=null){
				jedisUtils.returnBrokenResource(jedis);  
			}
			e.printStackTrace();
			
			return R.error(2,"登录失败！");
		}finally{
			if(jedis!=null){
				jedisUtils.returnResource(jedis); 
			}
		}
		
		return R.error(2,"注册用户失败！");
	}

	@RequestMapping("/toSignup")
	public String toSignup(HttpServletRequest request,Model model){
		initWebUser(request,model);
		String viewName = "";
		
		if(request.getSession().getAttribute("webUser")==null){
			viewName = "login/login";
		} else {
			viewName = "redirect:http://www.xqstudy.cn/enlistapi/zxbm.html";
		}
		
		return viewName;
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
}
