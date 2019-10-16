package com.study.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.dao.UserMapper;
import com.study.model.User;
import com.study.security.UserRealm;
import com.study.util.ShiroUtils;

@Controller
@RequestMapping("/admin")
public class loginController {
	Log log=LogFactory.getLog(loginController.class);
	@Autowired UserMapper usermapper;
	
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestBody User user){
		Subject subject = ShiroUtils.getSubject();
		log.info(user);
		User user1=usermapper.qryUserByPhone(user.getPhonenumber());
        //sha256加密
		String password=new Md5Hash(user.getUpassword()).toHex();
       //String password = new Sha256Hash(user.getUpassword()).toHex();	
       UsernamePasswordToken token = new UsernamePasswordToken(user1.getUname(), password);
       subject.login(token);
		return null;		
	}
	
	@Test
	public void test() {
	    ApplicationContext context =
	            new ClassPathXmlApplicationContext("platform-redis.xml");
	    RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
	    
	    Student student = new Student();
	    student.setName("我没有三颗心脏");
	    student.setAge(21);
	    redisTemplate.opsForValue().set("student_1", student);
	    Student student1 = (Student) redisTemplate.opsForValue().get("student_1");
	    student1.service();
	}
}
