package com.jt.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.order.mapper.OrderShippingMapper;

@Service
public class OrderShippingServiceImpl implements OrderShippingService {
    @Autowired
	private OrderShippingMapper orderShippingMapper;
}
