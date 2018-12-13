package com.jt.redis;

import org.junit.Test;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestRedis {
   
	//测试字符串
	@Test
	public void testString(){
		Jedis jedis = new Jedis("192.168.161.137", 6379);
		jedis.set("aa", "1807班级");
		String aa = jedis.get("aa");
		System.out.println(aa);
	}
	//测试存储hash
	@Test
	public void testHash(){
		Jedis jedis = new Jedis("192.168.161.137", 6379);
		jedis.hset("cat", "id", "24");
		jedis.hset("cat", "name", "小白");
		jedis.hset("cat", "age", "3");
		System.out.println(jedis.hgetAll("cat"));//返回Map		
	}
	//测试List存储
	@Test
	public void testList(){
		Jedis jedis = new Jedis("192.168.161.137", 6379);
		jedis.lpush("list", "熊大","熊二","熊三");
		System.out.println(jedis.lpop("list"));
		System.out.println(jedis.lpop("list"));
		System.out.println(jedis.lpop("list"));		
	}
	@Test
	public void testTx(){
		Jedis jedis = new Jedis("192.168.161.137", 6379);
		Transaction transtion = jedis.multi();
		transtion.set("aa", "sda");
		transtion.set("dd", "fds");
		transtion.set("ff", "dsad");
		transtion.exec();
		System.out.println(jedis.get("aa"));		
	}
	
}
