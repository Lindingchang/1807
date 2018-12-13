package com.jt.web.intercept;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.User;
import com.jt.web.thread.UserThread;

import redis.clients.jedis.JedisCluster;

//定义用户拦截器
public class UserIntercept implements HandlerInterceptor{
	@Autowired
	private JedisCluster jedisCluster;
	private ObjectMapper objetMapper = new ObjectMapper();
/**
 * 获取用户cookie得到token数据
 * 判断是否有数据，没有则重定向到登录页面
 * 若有则到redis集群查询是否有user信息，判断
 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = null;
	  Cookie[] cookies = request.getCookies();
	  for (Cookie cookie : cookies) {
		if(("JT_TICKET").equals(cookie.getName())){
			token = cookie.getValue();
			break;
		}
	}
	  //判断token
	  if(token!=null){
    //判断redis是否有数据
		  String userJson = jedisCluster.get(token);
		  User user = objetMapper.readValue(userJson, User.class);
		  if(userJson!=null){
			  UserThread.set(user);
			  return true;
		  }
	  }
	    response.sendRedirect("/user/login.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
				
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			//关闭ThreadLocal
		    UserThread.remove();
	}

}
