package com.study.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.study.model.chatUser;

@ServerEndpoint("/websocket/czd/{userno}")
@Component
public class improveWebsocket {
	private static CopyOnWriteArraySet<improveWebsocket> sessions = new CopyOnWriteArraySet<improveWebsocket>();
    /*
     * 线程不安全
     */
    //private volatile static List<Session> sessions = Collections.synchronizedList(new ArrayList());
    private  Session session;
    Map map = new HashMap();
    private String publisher = "";

    /*
     * 链接成功后的回掉
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userno") String param, Session session) throws IOException {
        System.out.println("链接成功");
        String userno = param;
		 map.put(session.getId(),param);
        this.session = session;
        sessions.add(this);
    }

    public void sendUserLocal(String clientInfoJson) {
        if (sessions.size() != 0) {
		    for (improveWebsocket s : sessions) {
		        if (s != null) {
		        	chatUser user = new chatUser();
			    	user.setId(publisher);
			    	user.setMessage(clientInfoJson);
		            // 判断是否为终端信息。如果是终端信息则查询数据库获取detail
		                //s.session.getBasicRemote().sendText(clientInfoJson);
		                //s.session.getAsyncRemote().sendObject(user);
		                try {
							s.session.getBasicRemote().sendText(JSON.toJSON(user).toString());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		         }
		}
      }
    }
    @OnError//连接错误的时候执行
	 public void onError(Throwable error,Session session) {
		 System.out.println("错误的时候执行");
		  error.printStackTrace();
	 }
    @OnMessage//收到消息执行
	 public void onMessage(String message,Session session) throws IOException {
		System.out.println(message+"**********");
		publisher=map.get(session.getId()).toString();
		sendUserLocal(message);	
	 }

    @OnClose
    public void onClose() {
        System.out.println("设置离线");
        sessions.remove(this);
    }
    
   
	
}
