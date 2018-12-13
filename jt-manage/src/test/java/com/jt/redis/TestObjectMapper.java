package com.jt.redis;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

import redis.clients.jedis.Jedis;

public class TestObjectMapper {
  //将对象转化为json
	@Test
	public void testObjectMapper() throws IOException{
		 User user = new User();
		 user.setId(1);
		 user.setName("小娥");
		 user.setSex("女");		 
		 ObjectMapper mapper = new ObjectMapper();
		 String json = mapper.writeValueAsString(user);
	     System.out.println(json);	
	     User user1 = mapper.readValue(json, User.class);
	     System.out.println(user1);
	}
}
