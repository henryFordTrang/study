package com.study.rabbitMQ;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.study.util.JsonUtil;
public class listener implements MessageListener  {

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		String msg=  new String (message.getBody());
		//MqMessage mqMsg = JsonUtil.parseObject(msg, MqMessage.class);
		System.err.println("onmessage监听"+msg);
	}

}
