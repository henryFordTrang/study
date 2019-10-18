package com.study.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import com.study.service.redis;
import com.study.util.ShiroUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping("/admin")
public class loginController {
	Log log=LogFactory.getLog(loginController.class);
	@Autowired UserMapper usermapper;
	@Autowired redis red;	
	@Autowired JedisPool jedisPool;
	
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestBody User user){
		Subject subject = ShiroUtils.getSubject();
		log.info(user);
		User user1=usermapper.qryUserByPhone(user.getPhonenumber());
        //sha256加密
		String password=new Md5Hash(user.getUpassword()).toHex();
       //String password = new Sha256Hash(user.getUpassword()).toHex();	
       UsernamePasswordToken token = new UsernamePasswordToken(user.getPhonenumber(), password);
       try {
    	   subject.login(token);
       } catch (UnknownAccountException e) {
		// TODO: handle exception
       }
       
		return null;		
	}
	
	@RequestMapping("/login1")
	@ResponseBody
	public String login1(@RequestBody User user){
//		test();
//		return "xxxx";
//		
		//Jedis jedis = new Jedis("192.168.10.3", 6379, 100000);
		Jedis yyy=jedisPool.getResource();
		//String xxx=jedis.get("loginInfo");
		String xxx=yyy.get("loginInfo");
		if(xxx!=null){
			log.info("data from memory............");
			return xxx;
		}else{
			log.info("data from data base");
			yyy.set("loginInfo", red.cacheable(18));			
			return red.cacheable(18);
		}
			
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String login2(){
//		test();
//		return "xxxx";
//		
		Jedis jedis = jedisPool.getResource();
		jedis.del("loginInfo");
		
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
