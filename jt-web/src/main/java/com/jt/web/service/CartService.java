package com.jt.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jt.common.po.Cart;

@Service
public interface CartService {

	List<Cart> findCartByUserId(Long userId);

	void saveCart(Cart cart);

	void updateCartNum(Long userId, Long itemId, Integer num);

	void deleteCart(Long userId, Long itemId);

}
