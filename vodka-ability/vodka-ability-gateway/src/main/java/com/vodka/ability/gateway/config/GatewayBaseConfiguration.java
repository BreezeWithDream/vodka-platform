package com.vodka.ability.gateway.config;

import com.vodka.ability.gateway.nacos.NacosDataSourceLoader;
import com.vodka.ability.gateway.properties.NacosDataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Breeze
 * @date 2023/9/17 16:55
 * @description
 */
@Configuration
@EnableConfigurationProperties(NacosDataSourceProperties.class)
public class GatewayBaseConfiguration {

    @Bean
    public NacosDataSourceProperties nacosDataSourceProperties() {
        return new NacosDataSourceProperties();
    }

    @Bean
    public NacosDataSourceLoader nacosDataSourceLoader(){
        return new NacosDataSourceLoader();
    }
}
