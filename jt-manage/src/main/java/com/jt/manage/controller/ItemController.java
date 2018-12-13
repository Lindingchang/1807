package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item/")
public class ItemController {
    @Autowired
	private ItemService itemService;
    
    /**
     * 返回json格式记录
     * 
     */
    @RequestMapping("query")
    @ResponseBody
    public EasyUIResult findItemByPage(Integer page,Integer rows){
    	return itemService.findItemByPage(page,rows);
    }
    /**
     * @ResponseBody进行数据解析时，如果是对象默认格式都采用utf-8的格式解析
     * 如果解析的数据是String字符串类型则按照ISO8859-1的格式进行解析
     * @param itemId
     * @return
     */
    //根据商品分类查询商品名称
    @RequestMapping(value="cat/queryItemName",produces="text/html;charset=utf-8")
    @ResponseBody
    public String findItemNameById(Long itemId){
    	return itemService.findItemNameById(itemId);
    }
    
    //实现商品新增
    @ResponseBody
    @RequestMapping("save")
    public SysResult saveItem(Item item,String desc){
    	try {
        itemService.saveItem(item,desc);       
		return SysResult.oK();	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return SysResult.build(201, "新增商品失败");
    }
    @ResponseBody
    @RequestMapping("update")
   public SysResult updateItem(Item item,String desc){
    	try {
            itemService.updateItem(item,desc);
    		return SysResult.oK();	
    		} catch (Exception e) {
    			e.printStackTrace();
    		}        	
        	return SysResult.build(201, "商品更新失败");
   }
    /**
     * 实现商品上架
     * 使用集合接收数据spring MVC自动拆串
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("reshelf")
   public SysResult reshelf(String[] ids){
    	try {
    		int status = 1;//上架
            itemService.updateStatus(ids,status);
    		return SysResult.oK();	
    		} catch (Exception e) {
    			e.printStackTrace();
    		}        	
        	return SysResult.build(201, "商品上架失败");
   }
     
    @ResponseBody
    @RequestMapping("instock")
   public SysResult instock(String[] ids){
    	try {
    		int status = 2;//下架
            itemService.updateStatus(ids,status);
    		return SysResult.oK();	
    		} catch (Exception e) {
    			e.printStackTrace();
    		}        	
        	return SysResult.build(201, "商品下架失败");
   }  
    //实现批量删除
    @RequestMapping("delete")
    @ResponseBody
    public SysResult delete(String [] ids){
    	try {
    		itemService.deleteItems(ids);
    		return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SysResult.build(201, "商品删除失败");
    }
    
    //以id查询商品描述信息
    @RequestMapping("query/item/desc/{itemId}")
    @ResponseBody
    public SysResult findItemDesc(@PathVariable Long itemId){
    	try {
			ItemDesc itemDesc = itemService.findItemDescById(itemId);
			
			return SysResult.oK(itemDesc);
		} catch (Exception e) {			
			e.printStackTrace();
		}
    	return SysResult.build(201, "商品描述回显失败");
    			
    }
    
}
