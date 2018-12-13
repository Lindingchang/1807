package com.jt.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private JedisCluster jedisCluster;
	@Autowired
	private UserService userService;
	//实现页面通用跳转
	@RequestMapping("{moduleName}")
	public String doLoginAndRegister(@PathVariable String moduleName){
		return moduleName;
	}

	@RequestMapping("doRegister")
	@ResponseBody
	public SysResult saveUser(User user){
		userService.saveUser(user);
		return SysResult.oK();

	}
	@RequestMapping("doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletResponse response){
		try {
			String token = userService.findTokenByUP(user);
			if(StringUtils.isEmpty(token)){
				throw new RuntimeException();
			}
			Cookie cookie = new Cookie("JT_TICKET", token);
			cookie.setMaxAge(3600*24*7);
			cookie.setPath("/");//设置权限
			response.addCookie(cookie);		 

			return SysResult.build(200, "登录成功");	 
		} catch (Exception e) {

		}

		return SysResult.build(201, "登录失败"); 
	}
	@RequestMapping("logout")
	public String logout(HttpServletResponse response,HttpServletRequest request){
		//删除redis缓存
		String token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("JT_TICKET")){
				token = cookie.getName();
				break;
			}
		}
		jedisCluster.del(token);
		//删除Cookie
		Cookie cookie = new Cookie("JT_TICKET", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/index.html"; 
	}

}