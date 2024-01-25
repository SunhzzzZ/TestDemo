package com.test.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dao.MallOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

@Component
public class MallOrderCache {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    Jedis jedis;

    public MallOrder getOrder(String orderId){
        MallOrder mallOrder = new MallOrder();

        String jsonData = jedis.get(orderId);

        // 缓存未命中
        if(!StringUtils.hasLength(jsonData)){
            return null;
        }

        try {
            // 缓存命中,json转对象
            ObjectMapper objectMapper = new ObjectMapper();
            mallOrder = objectMapper.readValue(jsonData,MallOrder.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return mallOrder;
    }

    /**
     * 设置缓存
     * @param jsonOrder
     * @return
     */
    public String setOrder(String orderId,String jsonOrder){
        return jedis.set(orderId,jsonOrder);
    }

}
