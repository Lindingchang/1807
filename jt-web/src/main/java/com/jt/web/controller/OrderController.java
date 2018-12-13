package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.po.Cart;
import com.jt.common.po.Order;
import com.jt.common.po.User;
import com.jt.common.vo.SysResult;
import com.jt.web.service.CartService;
import com.jt.web.service.OrderService;
import com.jt.web.thread.UserThread;

@Controller
@RequestMapping("/order/")
public class OrderController {
	@Autowired
   private CartService cartService; 
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("create")
	public String create(Model model){
		Long userId = UserThread.get().getId();
		List<Cart> cartList = cartService.findCartByUserId(userId);
		model.addAttribute("carts", cartList);
		return "order-cart";
		}
	
	/*http://www.jt.com/service/order/submit
	 * 实现订单入库 跳转时返回orderid
	 * */
	@RequestMapping("submit")
	@ResponseBody
   public SysResult saveOrder(Order order){
	   try {
		  Long userId = UserThread.get().getId(); 
		  order.setUserId(userId);
		  String orderId = orderService.saveOrder(order);
		  if(StringUtils.isEmpty(orderId)){
			throw new RuntimeException();  
		  }
		   return SysResult.oK(orderId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return SysResult.build(201, "订单入库失败");
   }
	@RequestMapping("success")
 public String findOrder(String id,Model model){
	   Order order =  orderService.findOrderById(id);
	   model.addAttribute("order", order);
		return "success";
 }
}
