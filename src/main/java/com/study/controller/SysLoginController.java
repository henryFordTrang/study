package com.study.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.study.security.logInterceptor;
import com.study.util.ShiroUtils;

/**
 * 登录相关
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月10日 下午1:15:31
 */
@Controller
@RequestMapping("sys")
public class SysLoginController {
    @Autowired
    private Producer producer;
    
    private Log log = LogFactory.getLog(SysLoginController.class);
    @RequestMapping("captcha")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
    	ServletOutputStream out = null;
    	try {
    		 response.setHeader("Cache-Control", "no-store, no-cache");
    		 response.setDateHeader("Expires", 0);
             response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
             response.addHeader("Cache-Control", "post-check=0, pre-check=0");
             response.setHeader("Pragma", "no-cache");
             response.setContentType("image/jpeg");
    	        System.out.println("***************sniper******************");
    	        //生成文字验证码
    	        String text = producer.createText();
    	        //生成图片验证码
    	        BufferedImage image = producer.createImage(text);
    	        //保存到shiro session
    	        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
    	        out = response.getOutputStream();
    	        ImageIO.write(image, "jpg", out);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(out!=null){
				out.close();
			}
		}
       
    }
    @RequestMapping("/login")
	@ResponseBody
	public String shiroLogin(String username, String password, String captcha){
		log.info("---user---"+username+"--password--"+password+"---captcha---"+captcha);
		String orgcaptcha=ShiroUtils.getSessionAttribute("KAPTCHA_SESSION_KEY").toString();
		Subject subject = ShiroUtils.getSubject();
        //sha256加密
        password = new Sha256Hash(password).toHex();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
		return null;
	}

}
