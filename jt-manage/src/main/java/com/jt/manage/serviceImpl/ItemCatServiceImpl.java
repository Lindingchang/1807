package com.jt.manage.serviceImpl;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.factory.JedisClusterFactory;
import com.jt.common.po.ItemCat;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUITree;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Autowired
    private JedisCluster jedisCluster;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public List<EasyUITree> findCatListById(Long parentId) {
		//根据父级id查询商品分类信息
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		List<ItemCat> itemCatList = itemCatMapper.select(itemCat);
		ArrayList<EasyUITree> treeList = new ArrayList<>();
		for (ItemCat itemCatTemp : itemCatList) {
			EasyUITree easyTree = new EasyUITree();
			easyTree.setId(itemCatTemp.getId());
			easyTree.setText(itemCatTemp.getName());
			String state = itemCatTemp.getIsParent()?"closed":"open";
			easyTree.setState(state);
			treeList.add(easyTree);
		}
		return treeList;
	}

	@Override
	public List<EasyUITree> findCacheCatList(Long parentId) {
		//JedisCluster jedisCluster=null;
		List<EasyUITree> listTree = new ArrayList<>();
		try {
			//jedisCluster = jedisClusterFactory.getObject();
			String key = "Item_Cat_"+parentId;
			//查询redis缓存数据		
			String json = jedisCluster.get(key);
			if(StringUtils.isEmpty(json)){
				listTree = findCatListById(parentId);
				//	System.out.println("第一次查询数据库");
				//转化为json
				json = objectMapper.writeValueAsString(listTree);
				//将json插入redis
				jedisCluster.set(key, json);
			} else{
				
				//将json转化为对象
				listTree =objectMapper.readValue(json, listTree.getClass());
				//		System.out.println("第一次查询缓存成功");			
			}
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
		//判断数据是否为空
		return listTree ;	
	}


}
