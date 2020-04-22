package com.ajb.common.utils;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.ajb.common.redis.shiro.JedisUtils;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import redis.clients.jedis.Jedis;

public class SmsUtils {
	
	//短信账号
	private static final String ACCESSKEYID = "";
	//短信密码
	private static final String ACCESSSECRET = "";
	//区域ID
	private static final String REGIONID = "";
	//短信签名
	private static final String SIGNNAME = "";
	//短信模板CODE
	private static final String TEMPLATECODE = "";

	public static void main(String[] args) {
//		sendSmsMessageCode("13666666666");
	}
	// 发送验证码
	public static SmsMessage sendSmsMessageCode(HttpServletRequest request,String mobile){
        DefaultProfile profile = DefaultProfile.getProfile(REGIONID, ACCESSKEYID, ACCESSSECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        //短信验证码
        String smsCode = "";
        smsCode = getSmsCode();
        System.out.println("smsCode---"+smsCode);
        CommonRequest commonRequest = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        commonRequest.setMethod(MethodType.POST);
        commonRequest.setDomain("dysmsapi.aliyuncs.com");
        commonRequest.setVersion("2017-05-25");
        commonRequest.setAction("SendSms");
        commonRequest.putQueryParameter("RegionId", REGIONID);
        commonRequest.putQueryParameter("PhoneNumbers", mobile);
        commonRequest.putQueryParameter("SignName", SIGNNAME);
        commonRequest.putQueryParameter("TemplateCode", TEMPLATECODE);
        commonRequest.putQueryParameter("TemplateParam", "{\"code\":\""+smsCode+"\"}");
        
        SmsMessage smsMessage = new SmsMessage();
        try {
            CommonResponse response = client.getCommonResponse(commonRequest);

            if(response.getData()!=null){
            	smsMessage = (SmsMessage) JSON.parseObject(response.getData(),SmsMessage.class);
            	smsMessage.setSmsCode(smsCode);
            }
            if("OK".equals(smsMessage.getCode())){
            	request.getSession().setAttribute(mobile, smsCode);
            }
            System.out.println(response.getData());
            
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        
        return smsMessage;
	}
	
	// 发送验证码(APP端)
	public static SmsMessage sendSmsMessageCodeApp(HttpServletRequest request,String mobile){
        DefaultProfile profile = DefaultProfile.getProfile(REGIONID, ACCESSKEYID, ACCESSSECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        SmsMessage smsMessage = new SmsMessage();
        JedisUtils jedisUtils = new JedisUtils();
        Jedis jedis = new Jedis();
        try {
        	jedis = jedisUtils.getJedis();
        	Properties props = jedisUtils.getProps();
			jedis.select(Integer.parseInt(props.get("login_session").toString()));
            //短信验证码
            String smsCode = "";
            smsCode = getSmsCode();
            System.out.println("smsCode---"+smsCode);
            CommonRequest commonRequest = new CommonRequest();
            //request.setProtocol(ProtocolType.HTTPS);
            commonRequest.setMethod(MethodType.POST);
            commonRequest.setDomain("dysmsapi.aliyuncs.com");
            commonRequest.setVersion("2017-05-25");
            commonRequest.setAction("SendSms");
            commonRequest.putQueryParameter("RegionId", REGIONID);
            commonRequest.putQueryParameter("PhoneNumbers", mobile);
            commonRequest.putQueryParameter("SignName", SIGNNAME);
            commonRequest.putQueryParameter("TemplateCode", TEMPLATECODE);
            commonRequest.putQueryParameter("TemplateParam", "{\"code\":\""+smsCode+"\"}");
            
            try {
                CommonResponse response = client.getCommonResponse(commonRequest);

                if(response.getData()!=null){
                	smsMessage = (SmsMessage) JSON.parseObject(response.getData(),SmsMessage.class);
                	smsMessage.setSmsCode(smsCode);
                }
                if("OK".equals(smsMessage.getCode())){
        			jedis.set(mobile, smsCode);
                }
                System.out.println(response.getData());
                
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        } catch (Exception e) { 
			if(jedis!=null){
				jedisUtils.returnBrokenResource(jedis);  
			}
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				jedisUtils.returnResource(jedis); 
			}
		}
        
        return smsMessage;
	}
	
	// 创建验证码
	public static String getSmsCode() {
		String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
		return random;
	}

}
