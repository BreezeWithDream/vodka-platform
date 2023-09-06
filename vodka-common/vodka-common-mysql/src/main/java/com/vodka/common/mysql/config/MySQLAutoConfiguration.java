package com.vodka.common.mysql.config;

import com.vodka.common.mysql.plugin.SQLPagePlugin;
import com.vodka.common.mysql.plugin.SQLRecordPlugin;
import com.vodka.common.mysql.properties.SQLRecordConfigProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@EnableConfigurationProperties(SQLRecordConfigProperties.class)
public class MySQLAutoConfiguration {

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
    public SQLPagePlugin sqlPagePlugin() {
        return new SQLPagePlugin();
    }

}
