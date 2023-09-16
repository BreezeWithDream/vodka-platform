package com.vodka.common.mysql.config;

import com.vodka.common.mysql.advice.PageResponseBodyAdvice;
import com.vodka.common.mysql.interceptor.PageRequestInterceptor;
import com.vodka.common.mysql.plugin.SQLPagePlugin;
import com.vodka.common.mysql.plugin.SQLRecordPlugin;
import com.vodka.common.mysql.properties.SQLPluginConfigProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Breeze
 * @date 2023/5/4 22:35
 * @description DB自动配置类
 */
@Configuration
@MapperScan("com.vodka.data.mapper")
@EnableTransactionManagement
@EnableConfigurationProperties(SQLPluginConfigProperties.class)
public class MySQLBaseConfiguration {

    /**
     * 向容器中注册SQL记录拦截器/插件
     * 根绝配置文件中的条件进行注册， 可拔插式
     *
     * @return SQLRecordPlugin
     */
    @Bean
    @ConditionalOnProperty(name = "vodka.plugins.sql.record.enable", havingValue = "true", matchIfMissing = false)
    public SQLRecordPlugin sqlRecordPlugin() {
        return new SQLRecordPlugin();
    }

    /**
     * SQL分页插件/拦截器, 注册到容器中
     *
     * @return SQLPagePlugin
     */
    @Bean
    @ConditionalOnProperty(name = "vodka.plugins.sql.page.enable", havingValue = "true", matchIfMissing = true)
    public SQLPagePlugin sqlPagePlugin() {
        return new SQLPagePlugin();
    }

    /**
     * 注册分页请求拦截器, 条件为配置文件中开启分页时进行注册
     *
     * @return PageRequestInterceptor
     */
    @Bean
    @ConditionalOnProperty(name = "vodka.plugins.sql.page.enable", havingValue = "true", matchIfMissing = true)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public PageRequestInterceptor pageInterceptor() {
        return new PageRequestInterceptor();
    }

    /**
     * 注册Page分页返回增强器
     *
     * @return PageResponseBodyAdvice
     */
    @Bean
    @ConditionalOnProperty(name = "vodka.plugins.sql.page.enable", havingValue = "true", matchIfMissing = true)
    public PageResponseBodyAdvice pageResponseBodyAdvice() {
        return new PageResponseBodyAdvice();
    }

}
