package com.jt.order.service;

import org.springframework.stereotype.Service;

import com.jt.common.po.Order;

@Service
public interface OrderService {

	String saveOrder(Order order);

	Order findOrderById(String id);

	
}
