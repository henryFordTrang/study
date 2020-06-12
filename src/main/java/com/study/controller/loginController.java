package com.study.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.study.dao.UserMapper;
import com.study.model.User;
import com.study.rabbitMQ.MqMessage;
import com.study.security.UserRealm;
import com.study.util.ShiroUtils;
import com.study.util.secutiryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin")
public class loginController {
	Log log=LogFactory.getLog(loginController.class);
	secutiryUtil securityutil=new secutiryUtil();
	@Autowired UserMapper usermapper;
	//@Autowired redis red;	
	//@Autowired JedisPool jedisPool;
	
//	@Autowired
//	private AmqpTemplate ss;
//	
//	@RequestMapping("/mqSend")
//	@ResponseBody
//	public void mqSend(){
//		MqMessage mqMessage = new MqMessage();
//		mqMessage.setSn("1111111111");
//		System.out.println("amqpTemplate....convertAndSend");
//		String queue = "queue.padbook69";
//		String exchange = "exchange.platform";
//		//ss.convertAndSend(queue, mqMessage);
//		ss.convertAndSend("queue.padbook69", "77777777");
//		//ss.convertAndSend(mqMessage);
//	}
	
	
	


	    
	@GetMapping("/html")
	public String helloFreeMarker(ModelMap model) {
	    System.err.println("**********html*****************");
	    //model.addAttribute("name","ITDragon博客");
	    //model.addObject("name", "wsnm");
	    model.addAttribute("name", "wsnm");
	    return "header";
	}
	
	@GetMapping("/jsp")
	public String jsp(ModelMap model) {
	    System.err.println("***********jsp****************");
	    //model.addAttribute("name","ITDragon博客");
	    //model.addObject("name", "wsnm");
	    model.addAttribute("name", "wsnm");
	    return "test";
	}
	
	@GetMapping("/header")
	public String header(ModelMap model) {
	    System.err.println("***********jsp****************");
	    //model.addAttribute("name","ITDragon博客");
	    //model.addObject("name", "wsnm");
	    model.addAttribute("name", "wsnm");
	    return "header2";
	}
	
	
	@RequestMapping("/login3")
	@ResponseBody
	public String login(@RequestBody User user,HttpServletRequest request){
		Subject subject = ShiroUtils.getSubject();
		log.info(user);
		User user1=usermapper.qryUserByPhone(user.getPhonenumber());
        //sha256加密
		String password=new Md5Hash(user.getUpassword()).toHex();
       //String password = new Sha256Hash(user.getUpassword()).toHex();	
       UsernamePasswordToken token = new UsernamePasswordToken(user.getPhonenumber(), password);
//       try {
//    	   subject.login(token);
//       } catch (UnknownAccountException e) {
//		// TODO: handle exception
//       }
       String ip=securityutil.getIpAddrByRequest(request);
       Session session=subject.getSession();
       session.setAttribute("ip", ip);
       request.getSession().setAttribute("addr", ip);
       System.out.println(session+"=============");
       System.out.println(request.getSession().toString()+"----------stringify---request----------");
		return null;		
	}
	
	@RequestMapping("/login2")
	@ResponseBody
	public String login2(@RequestBody User user){
		System.err.println(user.getPhonenumber()+"---"+user.getUpassword());
//       try {
//    	   	Subject subject = ShiroUtils.getSubject();
//	   		log.info(user);
//	   		User user1=usermapper.qryUserByPhone(user.getPhonenumber());
//	   		String password=new Md5Hash(user.getUpassword()).toHex();
//	          UsernamePasswordToken token = new UsernamePasswordToken(user.getPhonenumber(), password);
//    	   subject.login(token);
//       } catch (UnknownAccountException e) {
//		// TODO: handle exception
//       }
       
		return "bitch";		
	}
	
	@RequestMapping("/login1")
	@ResponseBody
	public String login1(@RequestBody User user){
//		test();
//		return "xxxx";
//		
		//Jedis jedis = new Jedis("192.168.10.3", 6379, 100000);
		//Jedis yyy=jedisPool.getResource();
		//String xxx=jedis.get("loginInfo");
//		String xxx=yyy.get("loginInfo");
//		if(xxx!=null){
//			log.info("data from memory............");
//			return xxx;
//		}else{
//			log.info("data from data base");
//			//yyy.set("loginInfo", red.cacheable(18));			
//			//return red.cacheable(18);
//			return null;
//		}
		return null;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String login2(HttpServletRequest request){
		Subject subject = ShiroUtils.getSubject();
		Session session=subject.getSession();
		String ip=securityutil.getIpAddrByRequest(request);
		String addr=(String) request.getSession().getAttribute("addr");
		System.out.println(session.getAttribute("ip")+"ip----------------"+ip);
		System.out.println(ip+"ip versus addr"+addr);
		if(ip.equals(session.getAttribute("ip"))){
			System.out.println("登录过,调用delete接口");
		}else{
			System.out.println("ip验证失败..............");
		}
		
		if(ip.equals(addr)){
			System.out.println("addr 验证成功");
		}else{
			System.out.println("addr验证失败..............");
		}
		return null;	
	}
	
	@Test
	public void test() {
//	    ApplicationContext context =
//	            new ClassPathXmlApplicationContext("platform-redis.xml");
//	    RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);	    
//	    Student student = new Student();
//	    student.setName("我没有三颗心脏");
//	    student.setAge(21);
//	    redisTemplate.opsForValue().set("student_1", student);
//	    Student student1 = (Student) redisTemplate.opsForValue().get("student_1");
//	    student1.service();
	}
}
