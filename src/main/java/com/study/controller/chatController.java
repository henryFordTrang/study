package com.study.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.java_websocket.WebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;	

@ServerEndpoint("/webSocket")  
public class chatController { 

	        private static int onlineCount = 0; 
	        private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>(); 

	        private Session session; 

	        private String username; 

	           

	        @OnOpen 
	        public void onOpen(@PathParam("username") String username, Session session) throws IOException { 

	       

	            this.username = username; 
	            this.session = session; 

	            System.out.println(username);   

	            addOnlineCount(); 

	            clients.put(username, null);

	            System.out.println("已连接");

	        } 

	       

	        @OnClose 

	        public void onClose() throws IOException { 

	            clients.remove(username); 

	            subOnlineCount(); 

	        } 

	       

	        @OnMessage 

	        public void onMessage(String message) throws IOException { 

	        	System.out.println(message);

//	            JSONObject jsonTo = JSONObject.fromObject(message); 
//
//	            String mes = (String) jsonTo.get("message");
//
//	             
//
//	            if (!jsonTo.get("To").equals("All")){ 
//
//	                sendMessageTo(mes, jsonTo.get("To").toString()); 
//
//	            }else{ 
//
//	                sendMessageAll("给所有人"); 
//
//	            } 

	        } 

	       

	        @OnError 

	        public void onError(Session session, Throwable error) { 

	            error.printStackTrace(); 

	        } 

	       

	        public void sendMessageTo(String message, String To) throws IOException { 

	            // session.getBasicRemote().sendText(message); 

	            //session.getAsyncRemote().sendText(message); 

	            for (WebSocket item : clients.values()) { 

	                //if (item.username.equals(To) ) 

	                    //item.session.getAsyncRemote().sendText(message); 

	            } 

	        } 

	           

	        public void sendMessageAll(String message) throws IOException { 

	            for (WebSocket item : clients.values()) { 

	                //item.session.getAsyncRemote().sendText(message); 

	            } 

	        } 

	       

	        public static synchronized int getOnlineCount() { 

	            return onlineCount; 

	        } 

	       

	        public static synchronized void addOnlineCount() { 

	            //WebSocket.onlineCount++; 

	        } 

	       

	        public static synchronized void subOnlineCount() { 

	            //WebSocket.onlineCount--; 

	        } 

	       

	        public static synchronized Map<String, WebSocket> getClients() { 

	            return clients; 

	        } 

	
}
