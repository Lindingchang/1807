package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Indexcontroller {
	
	@RequestMapping("index")
   public String index(){
	   return "index";
   }
	/**
	 * REStFul
	 * 1.在URL中将需要提交的参数使用“/”进行划分，localhost：8091/addUser/1/tom
	 * 2.在接收端，将参数使用{}包裹并将参数位置固定
	 * 3.变量的名称必须和{}中的名字一致
	 */
	@RequestMapping("page/{moudleName}")
	public String midule(@PathVariable String moudleName){
		
		return moudleName;
	}
	
}
