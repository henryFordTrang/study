package com.study.rabbitMQ;


import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import com.google.gson.Gson;

public class Gson2JsonMessageConverter extends AbstractJsonMessageConverter{

	@Override
	protected Message createMessage(Object object, MessageProperties messageProperties) {
		// TODO Auto-generated method stub
		System.out.println("Gson2JsonMessageConverter createMessage");
		return null;
	}

	@Override
	public Object fromMessage(Message message) throws MessageConversionException {
		// TODO Auto-generated method stub
		System.out.println("Gson2JsonMessageConverter fromMessage");
		return null;
	}

}
