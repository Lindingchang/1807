package com.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.sso.service.UserService;

import redis.clients.jedis.JedisCluster;
import sun.tools.jstack.JStack;

@Controller
@RequestMapping("/user/")
public class UserControll {
  
	@Autowired
	private UserService userService;
	@Autowired
	private JedisCluster  jedisCluster;
	@RequestMapping("check/{param}/{type}")
	@ResponseBody
	public MappingJacksonValue findCheckUser(@PathVariable String param,
			                                 @PathVariable Integer type,
			                                   String callback){
		Boolean flag = userService.findCheckUser(param,type);
		MappingJacksonValue value = new MappingJacksonValue(SysResult.oK(flag));
		value.setJsonpFunction(callback);
		return value;
	}
	@RequestMapping("register")
	@ResponseBody
	public SysResult saveUser(User user){
		 userService.saveUser(user);
		 return SysResult.oK();
	}
	//通过前台传递的用户名实现登录操作
	/*"http://sso.jt.com/user/login"*/
	@RequestMapping("login")
	@ResponseBody
	public SysResult findUserByUP(User user){
		try {
			String token = userService.findUserByUP(user);
			if(StringUtils.isEmpty(token)){
				throw new RuntimeException();
			}
			return SysResult.oK(token);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return SysResult.build(201, "用户登录失败");
	}
 //根据token查询用户信息
	@RequestMapping("query/{token}")
	@ResponseBody
	public MappingJacksonValue findUserByToken(@PathVariable String token,
			String callback){
		//获取redis中用户数据
		String userJson = jedisCluster.get(token);
		MappingJacksonValue jacksonValue = null;
		if(StringUtils.isEmpty(userJson)){
			jacksonValue = new MappingJacksonValue(SysResult.build(201, "用户信息查询失败"));			
		}else {
			jacksonValue = new MappingJacksonValue(SysResult.oK(userJson));	
		}
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
	}
}
