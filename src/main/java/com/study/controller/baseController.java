package com.study.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.study.model.User;
import com.study.util.Global;

public class baseController {
	public User getUser(){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(true);
		User usershiro = (User) session.getAttribute(Global.CURRENT_USER);
		return usershiro;
	}
}
