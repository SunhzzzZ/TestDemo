package com.test.exception;

import lombok.Data;

@Data
public class RedisCacheSetException extends RuntimeException{

    private String cacheName = "";

    public RedisCacheSetException(String cacheName){
        this.cacheName = cacheName;
    }
}
