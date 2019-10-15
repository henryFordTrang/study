package com.study.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
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
		System.out.println(77777777);
		User user1=usermapper.qryUserByPhone(user.getPhonenumber());
//		test();
//		return null;
//        //sha256加密
       String password = new Sha256Hash(user.getUpassword()).toHex();
	
       UsernamePasswordToken token = new UsernamePasswordToken(user.getPhonenumber(), password);
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
