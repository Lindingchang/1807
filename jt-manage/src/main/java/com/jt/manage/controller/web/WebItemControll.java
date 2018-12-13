package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/web/item/")
public class WebItemControll {
    @Autowired
	private ItemService itemService;
	@RequestMapping("findItemById/{itemId}")
	@ResponseBody
	public Item findItemById(@PathVariable Long itemId){
		return itemService.findItemById(itemId);
	}
	
	@RequestMapping("findItemDescById/{itemId}")
	@ResponseBody
	public ItemDesc fingItemDescById(@PathVariable Long itemId){
		return itemService.findItemDescById(itemId);
	}
}
