package com.jt.manage.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.manage.mapper.UserMapper;
import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  
	@Autowired
	private UserMapper usermapper;
	@Override
	public List<User> findAll() {
		
		return usermapper.findAll();
	}

}
