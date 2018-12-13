package com.jt.redis;

import java.util.HashSet;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestCluster {
	/**
	 * 1.创建集群操作对象
	 * 2.将集群操作的节点存入set集合中
	 */
	 @Test
      public void testRedisCluster(){
		HashSet<HostAndPort> nodes = new HashSet<>(); 
		 nodes.add(new HostAndPort("192.168.161.137", 7000));
		 nodes.add(new HostAndPort("192.168.161.137", 7001));
		 nodes.add(new HostAndPort("192.168.161.137", 7002));
		 nodes.add(new HostAndPort("192.168.161.137", 7003));
		 nodes.add(new HostAndPort("192.168.161.137", 7004));
		 nodes.add(new HostAndPort("192.168.161.137", 7005));
		 nodes.add(new HostAndPort("192.168.161.137", 7006));
		 nodes.add(new HostAndPort("192.168.161.137", 7007));
		 nodes.add(new HostAndPort("192.168.161.137", 7008));
		 nodes.add(new HostAndPort("192.168.161.137", 7009));
      JedisCluster jedisCluster = new JedisCluster(nodes);   
      jedisCluster.set("aa", "redis集群操作测试");
      System.out.println(jedisCluster.get("aa"));
      }
}
