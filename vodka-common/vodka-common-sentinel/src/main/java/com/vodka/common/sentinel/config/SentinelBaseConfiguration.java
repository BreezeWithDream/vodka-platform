package com.vodka.common.sentinel.config;

import com.vodka.common.sentinel.handler.SentinelExceptionHandler;
import com.vodka.common.sentinel.listener.SentinelExceptionListener;
import com.vodka.common.sentinel.nacos.NacosDataSourceLoader;
import com.vodka.common.sentinel.properties.NacosDataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Breeze
 * @date 2023/9/16 17:59
 * @description Sentinel基础配置类
 */
@Configuration
@EnableConfigurationProperties(NacosDataSourceProperties.class)
public class SentinelBaseConfiguration {

    @Bean
    public SentinelExceptionHandler sentinelExceptionHandler() {
        return new SentinelExceptionHandler();
    }

    /**
     * 注册Sentinel异常记录监听器
     *
     * @return SentinelExceptionListener
     */
    @Bean
    public SentinelExceptionListener sentinelExceptionListener() {
        return new SentinelExceptionListener();
    }

    /**
     * Nacos持久化
     *
     * @return
     */
    @Bean
    public NacosDataSourceLoader nacosDataSourceLoader() {
        return new NacosDataSourceLoader();
    }

}
