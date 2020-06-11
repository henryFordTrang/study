package com.study.security;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
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
		
		String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        log.info(userName+"===="+password);
        User user=usermapper.qryUserByPhone(userName);
      //账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }else if(!password.equals(user.getUpassword())){
        	throw new IncorrectCredentialsException("密码不正确");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        session.setAttribute(Global.CURRENT_USER, user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getPhonenumber(),
        		user.getUpassword(), getName());
        System.err.println(getName().toString()+info.toString());
		//SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, xxx, getName());
		return info;
	}

}
