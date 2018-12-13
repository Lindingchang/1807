package com.jt.sso.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.sso.mapper.UserMapper;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisCluster jedisCluster;
    private ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public Boolean findCheckUser(String param, Integer type) {
		User user = new User();
		switch (type) {
		case 1:
			user.setUsername(param); break;
			
		case 2:
			user.setPhone(param); break;
			
		case 3:
			user.setEmail(param); break;
		
			
		}
		//获取数据记录总数
		int count = userMapper.selectCount(user);
	   return count==0?false:true;
}

	@Override
	public void saveUser(User user) {
		//补全数据
		user.setEmail(user.getPhone());
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		try {
			userMapper.insert(user);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public String findUserByUP(User user) {
		//根据用户名和密码查询
		 List<User> userList = userMapper.select(user);
		 if(userList.size()==0){
			 return null;
		 }
		 User userDB = userList.get(0); 
		 String token = DigestUtils.md5Hex(
				 "JT_TICKET_"+System.currentTimeMillis()+user.getUsername());
		 try {
			String userJson = objectMapper.writeValueAsString(userDB);
			jedisCluster.setex(token,3600*24*7,userJson);
			
		} catch (JsonProcessingException e) {
					e.printStackTrace();
					throw new RuntimeException();
		}
		return token;
	}
}