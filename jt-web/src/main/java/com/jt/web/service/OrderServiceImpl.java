package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Order;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;

@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
	private HttpClientService httpClient;
    private ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public String saveOrder(Order order) {
		String url = "http://order.jt.com/order/create";
		String orderId = null;
		try {
			String orderJSON = objectMapper.writeValueAsString(order);
			HashMap<String, String> params = new HashMap<>();
			params.put("orderJSON", orderJSON);
			//发起请求
			String sysJson = httpClient.doPost(url, params);
			SysResult sysResult = objectMapper.readValue(sysJson, SysResult.class);
			if(sysResult.getStatus()==200){
				orderId = (String) sysResult.getData();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return orderId;
	}
	@Override
	public Order findOrderById(String id) {
		String url = "http://order.jt.com/order/query/"+id;
		String orderJson = httpClient.doGet(url);
		Order order = new Order();
		try {
			 order = objectMapper.readValue(orderJson, Order.class);
		} catch (IOException e) {
			System.out.println("获取数据失败");
			e.printStackTrace();
		}
		return order;
	}

}
