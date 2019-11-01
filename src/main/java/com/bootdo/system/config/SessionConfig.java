package com.bootdo.system.config;

import com.bootdo.system.filter.SessionFilter;
import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SessionConfig{

	/**
	 * 头攻击过滤拦截器
	 */
	@Bean
	public FilterRegistrationBean<SessionFilter> xssFilterRegistrationBean() {
		FilterRegistrationBean<SessionFilter> filterRegistrationBean = new FilterRegistrationBean<SessionFilter>();
		filterRegistrationBean.setFilter(new SessionFilter());
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = Maps.newHashMap();
		initParameters.put("excludes", "/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}
}
