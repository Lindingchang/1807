package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.Item;

 public interface ItemMapper extends SysMapper<Item>{
	//查询记录总数
	/**
	 * 不同的系统中的数据库对大小写，有不同的要求，
	 * 操作系统中，windows不区分大小写，但是在Linux中严格区分大小写
	 * @return
	 */
	@Select(value = { "select count(*) from tb_item" })
	int findItemCount();
   /**
    * Mybatis 接口不允许多值传输
    * 思路：封装到对象
    *  封装到集合
    * @param start
    * @param rows
    * @return
    */ 
	List<Item> findItemByPage(@Param("start") Integer start, @Param("rows") Integer rows);
	@Select("select name from tb_item_cat where id = #{itemId}")
    String findItemNameById(Long itemId);
	//实现批量更新
	void updateStatus(@Param("ids") String[] ids,@Param("status") int status);
	
}
