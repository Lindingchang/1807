package com.jt.manage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jt.manage.pojo.User;

@Service
public interface UserService {
  
	    //查询所有用户信息
		List<User> findAll();
}
