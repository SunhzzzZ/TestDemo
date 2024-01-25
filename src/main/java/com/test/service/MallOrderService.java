package com.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.dao.MallOrder;

public interface MallOrderService {

    int createOrder(String orderId,String userId);

    MallOrder getOrder(String orderId) throws JsonProcessingException;
}
