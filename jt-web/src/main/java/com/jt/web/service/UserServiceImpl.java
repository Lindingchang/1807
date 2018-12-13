package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;

@Service
public class UserServiceImpl implements UserService {
  
	@Autowired
	private HttpClientService httpClient;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public void saveUser(User user) {
		
		String url = "http://sso.jt.com/user/register";
		Map<String, String> params = new HashMap<>();
		params.put("username", user.getUsername());
		String md5pass = DigestUtils.md5Hex(user.getPassword());
		params.put("password", md5pass);
		params.put("phone", user.getPhone());
		params.put("email", user.getPhone());
		String resultJson = httpClient.doPost(url, params);
		try {
			SysResult sysResult = objectMapper.readValue(resultJson,SysResult.class);
			if(sysResult.getStatus()!=200){
				throw new RuntimeException();
			}
		} catch (IOException e) {			
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public String findTokenByUP(User user) {
		String token = null;
		String url = "http://sso.jt.com/user/login";
		String md5Pass= DigestUtils.md5Hex(user.getPassword());
		Map<String,String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", md5Pass);
		String resultJson = httpClient.doPost(url, params);
		try {
			SysResult sysResult = objectMapper.readValue(resultJson,SysResult.class);
			if(sysResult.getStatus()!=200){
				throw new RuntimeException();
			}
			token = (String) sysResult.getData();
		} catch (IOException e) {			
			e.printStackTrace();
			throw new RuntimeException();
		}
		return token;
	}
     
}
