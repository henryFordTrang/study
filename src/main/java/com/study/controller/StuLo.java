package com.study.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.dao.OrdersMapper;
import com.study.model.Orders;

@ServerEndpoint("/StuLo")
public class StuLo {
	
	private static Map<String,Session>stuL=new HashMap<String,Session>();
	public String aaa = null;
	@Autowired OrdersMapper ordermapper;
	
	@OnOpen
	public void open(Session session){	
		System.out.println("onopen..........");
		List<Orders>orders=new ArrayList<Orders>();
		orders=ordermapper.selectAllOrders();
		System.out.println(orders.toString());
		onMessage(aaa, session);
		return;
//		String name=session.getQueryString();		
//		String[]names=name.split("=");
//		aaa=names[1];
//		synchronized (stuL){
//			stuL.put(names[1], session);
//			Session session1=stuL.get("teahcer");
//			try {
//				session1.getBasicRemote().sendText("Login&"+names[1]);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//		}						
	}
	
	@OnMessage
	public void onMessage(String message,Session session){
		System.out.println("onmessage......."+message+"   "+session);
		return;
//		String []inst=message.split("&");		
//		System.out.println(inst[0]);		
//		if(inst[0].equals("peeping")){
//			synchronized (stuL){			
//			Session sessionPeep=stuL.get(inst[1]);
//			try {
//				sessionPeep.getBasicRemote().sendText(message);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		}				
//		else if(inst[0].equals("byforce")){			
//			Session sessionByforce=stuL.get(inst[1]);
//			try {
//				sessionByforce.getBasicRemote().sendText(message);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//		}		
//		else if(inst[0].equals("break")){			
//			Session sessionBreak=stuL.get(inst[1]);
//			try {
//				sessionBreak.getBasicRemote().sendText(message);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}			
//		}		
//		else if(inst[0].equals("pause")){			
//			Iterator iterator = stuL.entrySet().iterator();			
//			while (iterator.hasNext()) {
//				Entry<String, Session> entry = (Entry<String, Session>) iterator.next();												
//				Session remoteSession = entry.getValue();
//				try {
//					remoteSession.getBasicRemote().sendText(message);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}			
//		}		
//		else if(inst[0].equals("expSco")){			
//			Iterator iterator = stuL.entrySet().iterator();			
//			while (iterator.hasNext()) {
//				Entry<String, Session> entry = (Entry<String, Session>) iterator.next();												
//				Session remoteSession = entry.getValue();
//				try {
//					remoteSession.getBasicRemote().sendText(message);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}			
//		}		
//		else if(inst[0].equals("start")){						
//			synchronized (stuL){
//			Iterator iterator = stuL.entrySet().iterator();			
//			while (iterator.hasNext()) {
//				System.out.println("start instruction execute");
//				Entry<String, Session> entry = (Entry<String, Session>) iterator.next();
//
//				Session remoteSession = entry.getValue();
//				try {
//					
//					remoteSession.getBasicRemote().sendText(message);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		}
//
//		}
//		
//		
//		else if(inst[0].equals("end")){						
//			synchronized (stuL){
//			Iterator iterator = stuL.entrySet().iterator();			
//			while (iterator.hasNext()) {				
//				Entry<String, Session> entry = (Entry<String, Session>) iterator.next();
//				Session remoteSession = entry.getValue();
//				try {					
//					remoteSession.getBasicRemote().sendText(message);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}			
//		}
//		}												
	}	
	@OnClose
	public void onClose(Session session,CloseReason result){
		System.out.println("onclose.....");
		return;
//		synchronized (stuL){
//		stuL.remove(aaa);
//		}
	}
	

}
