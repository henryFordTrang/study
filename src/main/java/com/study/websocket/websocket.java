package com.study.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.java_websocket.WebSocket;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

@ServerEndpoint("/websocket/sniper")
public class websocket {
	private Session session;
	private CopyOnWriteArraySet<websocket> webSocketSet = new CopyOnWriteArraySet<websocket>();
	 @OnOpen//打开连接执行
	 public void onOpw(Session session) {
		 this.session=session;
		 System.out.println("打开了连接"+session);
		 webSocketSet.add(this);			
	 }
	 @OnMessage//收到消息执行
	 public void onMessage(String message,Session session) {
		System.out.println(message);
	try {
		sendMessage(message);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	 }
	 @OnClose//关闭连接执行
	 public void onClose(Session session) {
		 System.out.println("关闭连接");
		 webSocketSet.remove(this);
	 }
	 @OnError//连接错误的时候执行
	 public void onError(Throwable error,Session session) {
		 System.out.println("错误的时候执行");
		  error.printStackTrace();
	 }
       /*
       websocket  session发送文本消息有两个方法：getAsyncRemote()和
      getBasicRemote()  getAsyncRemote()和getBasicRemote()是异步与同步的区别，
      大部分情况下，推荐使用getAsyncRemote()。
     */
	 public void sendMessage(String message) throws IOException{
	    this.session.getAsyncRemote().sendText(message);
	    System.out.println();
		 for(websocket item: webSocketSet){
		
	            try {
	                item.sendMessage(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	                continue;
	            }
	        }
         }
}
