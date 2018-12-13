package com.jt.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class TestSentinel {
   @Test
	 public void test01(){
	   Set<String> sentinel = new HashSet<String>();
	    sentinel.add(new HostAndPort("192.168.161.137", 26379).toString());
	    System.out.println(new HostAndPort("192.168.161.137", 26379).toString());
		JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinel);
		Jedis jedis = sentinelPool.getResource();
		jedis.set("qw", "哨兵操作");
		System.out.println(jedis.get("qw"));
	 }
	
}
