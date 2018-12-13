package com.jt.web.service;

import org.springframework.stereotype.Service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;

@Service
public interface ItemService {

	Item findItemById(Long itemId);

	ItemDesc findItemDescById(Long itemId);
  
}
