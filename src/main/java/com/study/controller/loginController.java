package com.study.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.dao.UserMapper;
import com.study.model.User;
import com.study.util.ShiroUtils;

@Controller
@RequestMapping("/admin")
public class loginController {
	@Autowired UserMapper usermapper;
	@RequestMapping("/login")
	public String login(@RequestBody User user){
		Subject subject = ShiroUtils.getSubject();
		User user1=usermapper.qryUserByPhone(user.getPhonenumber());
        //sha256加密
       String password = new Sha256Hash(user.getUpassword()).toHex();
	
       UsernamePasswordToken token = new UsernamePasswordToken(user.getPhonenumber(), password);
       subject.login(token);
		return null;		
	}
}
