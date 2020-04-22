/**
 * 
 */
/**
 * @author tjjx.com
 *
 */
package com.ajb.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ajb.log.service.SysLogService;
import com.ajb.utils.IPUtils;
/**
 * 系统日志：切面处理类
 */


@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;
    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut(value = "@annotation(com.ajb.log.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);

        sysLog.setCreateTime(new Date());
        //获取用户名
        sysLog.setUserId(null);
        //获取用户ip地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        //调用service保存SysLog实体类到数据库
        sysLogService.save(sysLog);
    }

}