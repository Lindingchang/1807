package com.jt.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Cart;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	HttpClientService httpClient;
	ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public List<Cart> findCartByUserId(Long userId) {
		String url = "http://cart.jt.com/cart/query/"+userId;
		String jsonResult = httpClient.doGet(url);
		List<Cart> cartList = new ArrayList<>();
		try {
			SysResult sysResult = objectMapper.readValue(jsonResult,SysResult.class);
			
			if(sysResult.getStatus()==200){
				cartList = (List<Cart>) sysResult.getData();
			}else{
				System.out.println("后台查询数据失败");
				throw new RuntimeException();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return cartList;
	}
	@Override
	public void saveCart(Cart cart) {
		String url = "http://cart.jt.com/cart/save";
        //为了简化参数的传递将cart对象转化成json
		String cartJson = null;
		try {
			cartJson = objectMapper.writeValueAsString(cart);
		} catch (JsonProcessingException e) {
			System.out.println("参数转化异常");
			e.printStackTrace();
		}
		HashMap<String,String> params = new HashMap<>();
		params.put("cartJson", cartJson);
        httpClient.doPost(url, params);
	}
	@Override
	public void updateCartNum(Long userId, Long itemId, Integer num) {
		String url = "http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
		httpClient.doGet(url);
		
	}
	@Override
	public void deleteCart(Long userId, Long itemId) {
		String url = "http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		httpClient.doGet(url);
	}

}
