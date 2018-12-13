package com.jt.manage.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
	private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIResult findItemByPage(Integer page, Integer rows) {
		//int total = itemMapper.findItemCount();
		//通用Mapper的用法，传递对象
		int total = itemMapper.selectCount(null);
		int start = (page-1)*rows;
		List<Item> items = itemMapper.findItemByPage(start,rows);
		EasyUIResult easyUIResult = new EasyUIResult();
		easyUIResult.setRows(items);
		easyUIResult.setTotal(total);
		return easyUIResult;
	}

	@Override
	public String findItemNameById(Long itemId) {
		
		return itemMapper.findItemNameById(itemId);
	}

	@Override
	public void saveItem(Item item,String desc) {
		item.setStatus(1); //表示商品状态正常
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		//商品描述对象封装
		//描述表id与商品信息表id一样，如何赋值？
		//思路：对于操作数据库线程而言，每次入库都可以获取当前线程最大的id值，最后插入的值
		//select last insert_id(); mysql内置函数
		ItemDesc itemDesc = new ItemDesc();
		  itemDesc.setItemId(item.getId());//当执行插入操作时插件已经自动查询了这条数据，所以此时item是有id值的。
		  itemDesc.setItemDesc(desc);
		  itemDesc.setCreated(item.getCreated());
		  itemDesc.setUpdated(item.getCreated());
		  itemDescMapper.insert(itemDesc);
	}

	@Override
	public void updateItem(Item item,String desc) {
	 item.setUpdated(new Date());
	 itemMapper.updateByPrimaryKeySelective(item);
	 //更新商品描述信息
	 ItemDesc itemDesc = new ItemDesc();
	  itemDesc.setItemId(item.getId());
	  itemDesc.setItemDesc(desc);
	  item.setUpdated(item.getUpdated());
	  itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void updateStatus(String[] ids, int status) {
		
		 itemMapper.updateStatus(ids,status);
	}
     //一般先删除关联表中的数据，在删除主表的数据
	//数据库中的关联关系会影响程序的执行性能，后期数据库的所有表的关联关系都由程序员手动控制
	@Override
	public void deleteItems(String[] ids) {
		//先删除商品描述信息
		itemDescMapper.deleteByIDS(ids);
		//再删除商品信息
		itemMapper.deleteByIDS(ids);		
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {	
		ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return itemDesc;
	}

	@Override
	public Item findItemById(Long itemId) {
		
		return itemMapper.selectByPrimaryKey(itemId);
	}

	
}
