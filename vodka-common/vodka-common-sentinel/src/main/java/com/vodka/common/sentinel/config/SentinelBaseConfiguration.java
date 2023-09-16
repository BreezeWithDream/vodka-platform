package com.vodka.common.sentinel.config;

import com.vodka.common.sentinel.handler.SentinelExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Breeze
 * @date 2023/9/16 17:59
 * @description Sentinel基础配置类
 */
@Configuration
public class SentinelBaseConfiguration {

    @Bean
    public SentinelExceptionHandler sentinelExceptionHandler(){
        return new SentinelExceptionHandler();
    }
}
