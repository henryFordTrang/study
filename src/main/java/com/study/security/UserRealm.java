package com.study.security;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.study.dao.UserMapper;
import com.study.model.User;
import com.study.util.Global;

public class UserRealm extends AuthorizingRealm {
	@Autowired UserMapper usermapper;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Log log=LogFactory.getLog(UserRealm.class);
		log.info("-----------------------------henry-----------------------");
		System.out.println("-------------------ford----------------------------");
		User user = (User) principals.getPrimaryPrincipal();
        int userId = user.getUid();
        log.info("userId:"+userId);
        List<String> permsList = null;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		Log log=LogFactory.getLog(UserRealm.class);
		log.info("-----------------------------ngyuen-----------------------");
		System.out.println("-------------------trang----------------------------");
		String phonenumber = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
       User user=usermapper.qryUserByPhone(phonenumber);
        String xxx="92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e";
        System.out.println("phonenumber:"+phonenumber+"-----------password:"+password);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        session.setAttribute(Global.CURRENT_USER, user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getPhonenumber(),
                user.getUpassword(), getName());
		//SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, xxx, getName());
		return info;
	}

}
