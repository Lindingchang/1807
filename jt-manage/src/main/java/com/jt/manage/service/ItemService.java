package com.jt.manage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;

@Service
public interface ItemService {
     //查询所有商品
	EasyUIResult findItemByPage(Integer page,Integer rows);
  //查询商品名称
	String findItemNameById(Long itemId);
  //新增商品
	void saveItem(Item item, String desc);
  //更新商品
	void updateItem(Item item,String desc);
  //上下架
	void updateStatus(String[] ids, int status);

	void deleteItems(String[] ids);
	//根据id查询商品描述信息
	ItemDesc findItemDescById(Long itemId);
	Item findItemById(Long itemId);
	
	
}
