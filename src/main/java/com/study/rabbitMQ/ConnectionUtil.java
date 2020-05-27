package com.study.rabbitMQ;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil
{
    private static Logger logger = Logger.getLogger(ConnectionUtil.class);
    
    public static Connection getConnection()
    {
        try
        {
            Connection connection = null;
            //定义一个连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            //设置服务端地址（域名地址/ip）
            factory.setHost("49.233.85.23");
            //设置服务器端口号
            factory.setPort(5672);
            //设置虚拟主机(相当于数据库中的库)
            factory.setVirtualHost("/");
            //设置用户名
            factory.setUsername("sniper");
            //设置密码
            factory.setPassword("173205081");
//            factory.setHost("193.112.181.205");
//    		factory.setPort(5672);
//    		factory.setUsername("guest");
//    		factory.setPassword("guest");
            connection = factory.newConnection();
            return connection;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
