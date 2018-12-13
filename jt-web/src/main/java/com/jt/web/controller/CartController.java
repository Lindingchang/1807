package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Cart;
import com.jt.common.vo.SysResult;
import com.jt.web.service.CartService;
import com.jt.web.thread.UserThread;

@Controller
@RequestMapping("/cart/")
public class CartController {
    
	@Autowired
	private CartService cartService;
	//购物车展现
	@RequestMapping("show")
	public String show(Model model){
		Long userId = UserThread.get().getId();
	List<Cart> cartList = cartService.findCartByUserId(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	//购物车添加
	@RequestMapping("add/{itemId}")
	public String saveCart(@PathVariable Long itemId,Cart cart){
		Long userId = UserThread.get().getId();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";
	}
	//购物车数量更新
	@RequestMapping("update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(
			@PathVariable Long itemId,
			@PathVariable Integer num){
		try {
			Long userId = UserThread.get().getId();
			cartService.updateCartNum(userId,itemId,num);
			return SysResult.oK();
		} catch (Exception e) {
			
		}
		return SysResult.build(201, "购物车更新失败");
	}
	//删除购物车商品
	@RequestMapping("delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId){
		 try {
			 Long userId = UserThread.get().getId();
			 cartService.deleteCart(userId,itemId);
			
		} catch (Exception e) {
			System.out.println("购物车删除失败");
			e.printStackTrace();
			
		}
		return "redirect:/cart/show.html";
		 
	}
	
}
