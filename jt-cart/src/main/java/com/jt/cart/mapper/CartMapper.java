package com.jt.cart.mapper;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.Cart;

public interface CartMapper extends SysMapper<Cart>{

	void updateCartNum(Cart cart);

	Cart findCartByUI(Cart cart);

	void deleteCartByUI(@Param("userId") Long userId,@Param("itemId") Long itemId);
   
	
}
