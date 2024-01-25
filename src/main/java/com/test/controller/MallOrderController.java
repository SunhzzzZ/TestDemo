package com.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.contant.STATUS_FLG_ENUM;
import com.test.dao.MallOrder;
import com.test.service.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // 跨域
@RestController
@RequestMapping("/order")
public class MallOrderController {

    @Autowired
    MallOrderService mallOrderService;

    @GetMapping("/create/{orderId}/{userId}")
    public STATUS_FLG_ENUM createOrder(@PathVariable("orderId") String orderId, @PathVariable("userId") String userId){

        int result = mallOrderService.createOrder(orderId,userId);

        if(0 != result){
            return STATUS_FLG_ENUM.SUCCESS;
        } else {
            return STATUS_FLG_ENUM.ERROR;
        }
    }

    @GetMapping("/get/{orderId}")
    public MallOrder getMallOrder(@PathVariable("orderId") String orderId) throws JsonProcessingException {
        return mallOrderService.getOrder(orderId);
    }
}
