package com.jt.web.service;

import org.springframework.stereotype.Service;

import com.jt.common.po.User;

@Service
public interface UserService {

	void saveUser(User user);

	String findTokenByUP(User user);
   
}
