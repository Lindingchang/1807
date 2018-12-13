package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.service.HttpClientService;

@Service
public class ItemServiceImpl implements ItemService {
	private ObjectMapper objectMapper= new ObjectMapper();
    @Autowired
	private HttpClientService httpClient;
	@Override
	public Item findItemById(Long itemId) {
		String url = "http://manage.jt.com/web/item/findItemById/"+itemId;
		//获取后台返回的json
		String result = httpClient.doGet(url);
		Item item = null;
		try {
			item = objectMapper.readValue(result, Item.class);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return item;
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url = "http://manage.jt.com/web/item/findItemDescById/"+itemId;
		String result = httpClient.doGet(url);
		ItemDesc itemDesc = null;
		try {
			itemDesc = objectMapper.readValue(result, ItemDesc.class);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return itemDesc;
	}

}
