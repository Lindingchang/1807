package com.jt.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.order.mapper.OrderItemMapper;

@Service
public class OrderItemServiceImpl implements OrderItemService {
   @Autowired
	private OrderItemMapper orderItemMapper;
}
