package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfig {

    @Bean
    public Jedis jedis(){
        /**
         * 连接阿里云redis服务器、
         * 1.vim redis.conf配置文件
         *      protected-mode no允许远程连接
         *      bind 127.0.0.1注释掉
         *      requiredpass解开注释，设置密码（可选，如果设置密码则需要jedis.auth("密码");）
         * 2.添加端口6379的防火墙并重启
         * 3.阿里云服务器开启安全组配置添加端口号6379
         * 4.测试连接
         */
        Jedis jedis = new Jedis("121.40.255.172",6379);
        jedis.auth("sunhao1.");
        return jedis;
    }
}
