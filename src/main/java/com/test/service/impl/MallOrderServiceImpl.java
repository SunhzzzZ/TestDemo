package com.test.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cache.MallOrderCache;
import com.test.dao.MallOrder;
import com.test.mapper.MallOrderMapper;
import com.test.service.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MallOrderServiceImpl implements MallOrderService {

    @Autowired
    MallOrderMapper mallOrderMapper;

    @Autowired
    MallOrderCache mallOrderCache;



    @Override
    public int createOrder(String orderId, String userId) {
        return mallOrderMapper.createOrder(orderId, userId);
    }

    @Override
    public MallOrder getOrder(String orderId) throws JsonProcessingException {

        // 获取缓存数据
        MallOrder mallOrder = mallOrderCache.getOrder(orderId);

        // 缓存未命中，取DB
        if (null == mallOrder) {

            // DB取数据
            mallOrder = mallOrderMapper.getOrder(orderId);

            // 转json，存储Redis
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMallOrder = objectMapper.writeValueAsString(mallOrder);
            String setCacheResult = mallOrderCache.setOrder(mallOrder.getOrderId(),jsonMallOrder);

            return mallOrder;
        }
        // 缓存未命中，直接返回
        return mallOrder;
    }
}
