package com.jt.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.data.redis.connection.PoolConfig;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestshardsRedis {
   @Test
	public void test1(){
	   List<JedisShardInfo> shards = new ArrayList<>();
	    shards.add(new JedisShardInfo("192.168.161.139",7000));
	    shards.add(new JedisShardInfo("192.168.161.139",7001));
	    shards.add(new JedisShardInfo("192.168.161.139",7002));
		//实现连接池对象
	    PoolConfig config = new PoolConfig();
	    ShardedJedisPool pool = new ShardedJedisPool(config, shards);
	    ShardedJedis shardedJedis = new ShardedJedis(shards);
		for(int i =1;i<10;i++){
			shardedJedis.set("a"+i, "a"+i);	
		}
		System.out.println(shardedJedis.get("a5"));
	}
	
}
