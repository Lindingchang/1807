package com.jt.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.po.User;
import com.jt.sso.mapper.UserMapper;

@Service
public interface UserService {

	Boolean findCheckUser(String param, Integer type);

	void saveUser(User user);

	String findUserByUP(User user);
 	
}
