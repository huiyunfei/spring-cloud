package com.example.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hui.yunfei@qq.com on 2019/5/15
 * Feign禁用Hystrix功能
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
}
