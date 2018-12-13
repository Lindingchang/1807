package com.jt.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.cart.service.CartService;
import com.jt.common.po.Cart;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart/")
public class CartController {
    
	@Autowired
	private CartService cartService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping("query/{userId}")
	@ResponseBody
	 public SysResult findCartByUserId(@PathVariable Long userId){
		  try {
			SysResult sysResult= cartService.findCartByUserId(userId);
			 return sysResult;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		  return SysResult.build(201, "查询数据失败");
	 }
	@RequestMapping("update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(@PathVariable Long userId,
			                       @PathVariable Long itemId,
			                       @PathVariable Integer num){
		try {
			cartService.updateCartNum(userId,itemId,num);
			return SysResult.oK();
		} catch (Exception e) {
			
		}
		return SysResult.build(201, "购物车商品更新失败");
	}
	//购物车入库
	@RequestMapping("save")
	@ResponseBody
	public SysResult saveCart(String cartJson){
		  try {
			Cart cart = objectMapper.readValue(cartJson, Cart.class);
			cartService.saveCart(cart);
			  
			  return SysResult.oK();
		} catch (Exception e) {
			
		}
		  
		  return SysResult.build(201, "新增失败");
	}
	//购物车商品删除
	@RequestMapping("delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCartItem(@PathVariable Long userId,
			                        @PathVariable Long itemId){
		try {
			cartService.deleteCartByUI(userId,itemId);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "购物车商品删除失败");
	}
}
