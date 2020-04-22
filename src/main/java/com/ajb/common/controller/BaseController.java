package com.ajb.common.controller;

import org.springframework.stereotype.Controller;

import com.ajb.common.utils.ShiroUtils;
import com.ajb.system.domain.UserDO;
import com.ajb.system.domain.UserToken;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}