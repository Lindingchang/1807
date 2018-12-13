package com.jt.cart.service;

import org.springframework.stereotype.Service;

import com.jt.common.po.Cart;
import com.jt.common.vo.SysResult;

@Service
public interface CartService {

	SysResult findCartByUserId(Long userId);

	void updateCartNum(Long userId, Long itemId, Integer num);

	void saveCart(Cart cart);

	void deleteCartByUI(Long userId, Long itemId);

}
