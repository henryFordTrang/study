package com.study;

import java.io.File;
import java.io.InputStream;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.AbstractHash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class test {
	public static void main(String[] args){
		String x=new Sha256Hash("21218cca77804d2ba1922c33e0151105").toHex();
		String y=new Md5Hash("888888").toHex();
		String z=new Md5Hash("92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e").toHex();
		String r=new Md5Hash("46cc468df60c961d8da2326337c7aa58").toHex();
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		System.out.println(r);
		//redisTester();
	}
	
	
	
	@Test
	public static void redisTester() {
	    //Jedis jedis = new Jedis("192.168.10.3", 6379, 100000);
	    Jedis jedis = new Jedis("192.168.10.3", 6379, 100000);
	    
	    int i = 0;
	    try {
	        long start = System.currentTimeMillis();// 开始毫秒数
	        while (true) {
	            long end = System.currentTimeMillis();
	            if (end - start >= 1000) {// 当大于等于1000毫秒（相当于1秒）时，结束操作
	                break;
	            }
	            i++;
	            jedis.set("test" + i, i + "");
	        }
	    } finally {// 关闭连接
	        jedis.close();
	    }
	    // 打印1秒内对Redis的操作次数
	    System.out.println("redis每秒操作：" + i + "次");
	}
}
