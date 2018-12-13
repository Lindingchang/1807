package com.jt.jt_redis;


import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Unit test for simple App.
 */
public class AppTest 
  
{
	
		//完成单实例链接,修改代码中ip地址
	@Test
	public void jedis(){
		Jedis jedis = new Jedis("192.168.161.139", 7000);
		//jedis.auth("123456");
		jedis.set("name", "tony");	//调用redis命令set
		String s = jedis.get("name");
		System.out.println(s);
		jedis.close();
	}


}
