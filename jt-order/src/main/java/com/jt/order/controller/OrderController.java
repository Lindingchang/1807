package com.jt.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Order;
import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("/order/")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
    private ObjectMapper objectMapper = new ObjectMapper();
	
    @RequestMapping("create")
	@ResponseBody
	public SysResult saveOrder(String orderJSON){
		try {
			
			Order order = objectMapper.readValue(orderJSON, Order.class); 
			String orderId = orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)){
				return SysResult.oK(orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "订单生成失败");
	}
    @RequestMapping("query/{id}")
    @ResponseBody
	public Order findOrder(@PathVariable String id){
		Order order = new Order();
		order = orderService.findOrderById(id);
    	return order;
	}
}
