package com.jt.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.po.Order;
import com.jt.common.po.OrderItem;
import com.jt.common.po.OrderShipping;
import com.jt.order.mapper.OrderItemMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.mapper.OrderShippingMapper;

@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
	private OrderMapper orderMapper;
   @Autowired
    private OrderItemMapper orderItemMapper;
   @Autowired
    private OrderShippingMapper orderShippingMapper;

@Override
public String saveOrder(Order order) {
	Long userId = order.getUserId();
	String orderId = "" + userId  + System.currentTimeMillis();
	Date date = new Date();
	//实现订单信息入库
	order.setOrderId(orderId);
	order.setStatus(1);
	order.setCreated(date);
	order.setUpdated(date);
	orderMapper.insert(order);
	System.out.println("订单信息入库成功");
	//实现订单物流信息入库
	OrderShipping orderShipping = order.getOrderShipping();
	 orderShipping.setOrderId(orderId);
	 orderShipping.setCreated(date);
	 orderShipping.setUpdated(date);
	 orderShippingMapper.insert(orderShipping);
	 System.out.println("订单物流入库成功");
	//实现订单商品信息入库
	 List<OrderItem> orderItems = order.getOrderItems();
	 for (OrderItem orderItem : orderItems) {
		orderItem.setCreated(date);
		orderItem.setUpdated(date);
		orderItem.setOrderId(orderId);
		orderItemMapper.insert(orderItem);
	}
	 System.out.println("商品信息入库成功");
	return orderId;
}

@Override
public Order findOrderById(String id) {
	Order order = orderMapper.selectByPrimaryKey(id);
	OrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(id);
	OrderItem orderItem = new OrderItem();
	orderItem.setOrderId(id);
	List<OrderItem> orderItems = orderItemMapper.select(orderItem);
	order.setOrderShipping(orderShipping);
	order.setOrderItems(orderItems);
	return order;
}
}
