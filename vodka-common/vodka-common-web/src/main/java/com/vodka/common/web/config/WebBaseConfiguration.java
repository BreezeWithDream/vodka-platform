package com.vodka.common.web.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.vodka.common.web.exception.GlobalException;
import com.vodka.common.web.utils.VodkaAppContextUtil;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Breeze
 * @date 2023/4/26 21:53
 * @description Web层自动装配配置类
 */
@Configuration
public class WebBaseConfiguration {

    /**
     * 基础配置
     */
    @Configuration
    public static class Base {
        /**
         * 将ApplicationContext工具类注册到容器中
         *
         * @return VodkaAppContextUtil对象
         */
        @Bean
        public VodkaAppContextUtil vodkaAppContextUtil() {
            return new VodkaAppContextUtil();
        }
    }

    /**
     * WebMvc相关配置
     */
    @Configuration
    public static class WebMvc {
        /**
         * 注册全局异常处理类组件到容器中
         *
         * @return GlobalException
         */
        @Bean
        public GlobalException globalException() {
            return new GlobalException();
        }

        /**
         * 注册WebInterceptor注册器到容器中
         *
         * @return WebInterceptorRegister
         */
        @Bean
        public WebInterceptorRegister webInterceptorRegister() {
            return new WebInterceptorRegister();
        }
    }


    /**
     * Nacos相关配置
     */
    @Configuration
    @EnableDiscoveryClient
    public static class Nacos {

        /**
         * 配置Nacos服务发现属性统一配置
         *
         * @return NacosDiscoveryProperties
         */
        @Bean
        @Primary
        public NacosDiscoveryProperties nacosDiscoveryProperties() {
            NacosDiscoveryProperties nacosDiscoveryProperties = new NacosDiscoveryProperties();

            Map<String, String> metaData = new HashMap<>();
            metaData.put("onlineTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            nacosDiscoveryProperties.setMetadata(metaData);
            return nacosDiscoveryProperties;
        }
    }

    @Configuration
    @EnableFeignClients(basePackages = "com.vodka.business.feign")
    public static class Feign {

    }


}
