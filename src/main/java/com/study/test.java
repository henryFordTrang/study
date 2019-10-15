package com.study;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class test {
	public static void main(String[] args){
		String x=new Sha256Hash("888888").toHex();
		System.out.println(x);
		redisTester();
	}
	
	@Test
	public static void redisTester() {
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
