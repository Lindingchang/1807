package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.vo.EasyUITree;

@Service
public interface ItemCatService {

	

	List<EasyUITree> findCatListById(Long parentId);

	List<EasyUITree> findCacheCatList(Long parentId);
	
}
