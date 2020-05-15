package com.study;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.AbstractHash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class test {
	public static void main(String[] args){
		
	}
	
	public static void element(NodeList list){
        for (int i = 0; i <list.getLength() ; i++) {
            Element element = (Element) list.item(i);
            NodeList childNodes = element.getChildNodes();
            for (int j = 0; j <childNodes.getLength() ; j++) {
                if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
                    //获取节点
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    //获取节点值
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
        }
    }

    public static void node(NodeList list){
        for (int i = 0; i <list.getLength() ; i++) {
            Node node = list.item(i);
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j <childNodes.getLength() ; j++) {
                if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
        }
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
