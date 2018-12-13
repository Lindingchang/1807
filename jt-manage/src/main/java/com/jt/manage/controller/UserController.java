package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;

@Controller
@RequestMapping("/") 
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@RequestMapping("findAll")
  public String fingAll(Model model){
	   List<User> userList = userservice.findAll();
	  model.addAttribute("userList", userList);
		return "userList";
  }
	
}
