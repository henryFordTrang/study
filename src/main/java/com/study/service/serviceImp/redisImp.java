package com.study.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dao.UserMapper;
import com.study.service.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
@Transactional
public class redisImp implements redis {
	@Autowired UserMapper um;
	
	@Override
	public String cacheable(int key) {
		// TODO Auto-generated method stub
		
		return um.redisTest(key);
	}

}
