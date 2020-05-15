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
import com.study.model.chatUser;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
@ServerEndpoint("/websocket/sniper/{userno}")
public class websocket {
	private Session session;
	private String userno = "";
	private String publisher = "";
	Map map = new HashMap();
	
	private static ConcurrentHashMap<String, websocket> webSocketSet = new ConcurrentHashMap<String, websocket>();
	//private CopyOnWriteArraySet<websocket> webSocketSet = new CopyOnWriteArraySet<websocket>();
	 @OnOpen//打开连接执行
	 public void onOpw(@PathParam(value = "userno") String param,Session session) {
		 this.session=session;
		 userno = param;
		 map.put(session.getId(),param);
		 System.out.println("打开了连接"+session+userno+"连上来了");
		 //webSocketSet.add(this);
		 webSocketSet.put(param, this);
	 }
	 @OnMessage//收到消息执行
	 public void onMessage(String message,Session session) {
		System.out.println(message+"**********");
		publisher=map.get(session.getId()).toString();
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
	    for (String key : webSocketSet.keySet()) {
	    	System.out.println(key);
	    	chatUser user = new chatUser();
	    	user.setId(publisher);
	    	user.setMessage(message);
	    	System.out.println(user);
	    	System.out.println(JSON.toJSON(user));
	    	//String fb = "{\"id\":+key+","+"message:"+message+"}";
	    	webSocketSet.get(key).session.getAsyncRemote().sendText(JSON.toJSON(user).toString());
	    	webSocketSet.get(key).session.getAsyncRemote().sendObject(user);
	    	System.out.println(user.toString()+"*******");
//            try {
//                    webSocketSet.get(key).sendMessage("用户" + userno + "发来消息：" + " <br/> iiiooooiiiiooo");
//            	System.out.println(key);
//                
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
         }
}
