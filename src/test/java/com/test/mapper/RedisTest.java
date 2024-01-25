package com.test.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testSet(){
        redisTemplate.opsForValue().set("key2","3");
    }


    @Test
    public void testGet(){
        String key1 = redisTemplate.opsForValue().get("key1").toString();
        System.out.println(key1);
    }

    @Test
    public void jedisTest(){
        Jedis jedis = new Jedis("121.40.255.172",6379);
        jedis.auth("sunhao1.");
        System.out.println(jedis.ping());
    }

    @Test
    public void redisTest(){
        String v1 = (String) redisTemplate.opsForValue().get("k1");
        System.out.println(v1);
    }

    @Test
    public void testSetNx(){
    }
}
