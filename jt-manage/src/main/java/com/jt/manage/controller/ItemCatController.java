package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.service.ItemCatService;
import com.jt.manage.service.ItemService;
import com.jt.manage.vo.EasyUITree;

@RequestMapping("/item/cat/")
@Controller
public class ItemCatController {
	@Autowired
  private ItemCatService itemCatService;
	/**
	 * @RequestParam实现
	 * @param parentId
	 * @return
	 */
	
	@RequestMapping("list")
	@ResponseBody
	public List<EasyUITree> findItemCatListById(@RequestParam(value="id",defaultValue="0") Long parentId){
		//查询1级商品分类		
		return itemCatService.findCacheCatList(parentId);
	}
	
}
