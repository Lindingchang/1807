package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.common.po.Cart;
import com.jt.common.vo.SysResult;

@Service
public class CartServiceImpl implements CartService {
     @Autowired
	 private CartMapper cartMapper;
	
     @Override
	public SysResult findCartByUserId(Long userId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		List<Cart> ListCart = cartMapper.select(cart);		
		return SysResult.oK(ListCart);
	}

	@Override
	public void updateCartNum(Long userId, Long itemId, Integer num) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cart.setNum(num);
		cart.setUpdated(new Date());
		cartMapper.updateCartNum(cart);
		
	}
    //新增购物车业务
	@Override
	public void saveCart(Cart cart) {
		//先查看购物车里有没有此商品，若有则与之前数量相加，无则直接入库
	 	Cart cartDB = cartMapper.findCartByUI(cart);
	 	if(cartDB==null){
	 		cart.setCreated(new Date());
	 		cart.setUpdated(cart.getCreated());
	 		cartMapper.insert(cart);
	 	}else{
	 		int num = cartDB.getNum()+cart.getNum();
	 		cartDB.setNum(num);
	 		cart.setUpdated(new Date());
	 		cartMapper.updateByPrimaryKey(cartDB);
	 	}
	}
 //删除购物车商品
	@Override
	public void deleteCartByUI(Long userId, Long itemId) {
		cartMapper.deleteCartByUI(userId,itemId);
		
	}

}
